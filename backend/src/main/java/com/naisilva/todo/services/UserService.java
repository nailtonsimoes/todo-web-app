package com.naisilva.todo.services;

import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.roleDtos.RoleDtoRequest;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import com.naisilva.todo.dtos.userDtos.UserRequestDto;
import com.naisilva.todo.dtos.userDtos.UserResponseDto;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User saveUser(UserRequestDto request) {
        Optional<User> existingUser = userRepository.findByName(request.getName());

        if (existingUser.isPresent()) {
            throw new RuntimeException("usuario ja existe");
        }

        request.setPassword(passwordEncoder().encode(request.getPassword()));

        User user = new User();
        BeanUtils.copyProperties(request, user);

        return userRepository.save(user);
    }

    public Optional<User> getUserByUserName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<UserResponseDto> getUserByUserId(Long id) {
        Optional<User> userModelOptional = userRepository.findById(id);
        if (userModelOptional.isPresent()) {
            User user = userModelOptional.get();
            UserResponseDto userResponseDto = new UserResponseDto();

            BeanUtils.copyProperties(user, userResponseDto);

            List<RoleDtoRequest> roles = user.getRoles().stream()
                    .map( roleModel -> {
                        RoleDtoRequest role = new RoleDtoRequest(roleModel.getName());
                        return role;
                            }
                    )
                    .collect(Collectors.toList());

            userResponseDto.setRoles(roles);

            List<TodoDtoResponse> todos = user.getTodos().stream()
                    .map(todoModel -> {
                        TodoDtoResponse todo = new TodoDtoResponse();
                        BeanUtils.copyProperties(todoModel, todo);
                        return todo;
                    })
                    .collect(Collectors.toList());

            userResponseDto.setTodos(todos);
            return Optional.of(userResponseDto);
        } else {
            return Optional.empty();
        }
    }

    public List<UserResponseDto> listAllUsers() {
        List<User> listUsersDB = userRepository.findAll();

        List<UserResponseDto> listUsersResponse = listUsersDB
                .stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getPassword(),
                        user.getEmail(),
                        user.getRoles()
                                .stream()
                                .map(
                                        role -> new RoleDtoRequest(role.getName())
                                ).collect(Collectors.toList()),
                        user.getToken(),
                        user.getTodos()
                                .stream()
                                .map(
                                        todo -> new TodoDtoResponse(
                                                todo.getId(),
                                                todo.getTitle(),
                                                todo.getDescription(),
                                                todo.getDateForFinalize(),
                                                todo.getFinished(),
                                                todo.getUser().getId()
                                        )
                                ).collect(Collectors.toList()))).collect(Collectors.toList());

        return listUsersResponse;
    }

    public void updateUser(Long id, UserRequestDto request) {
        User userModel = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "Usuario não encontrado id: " + id + ", tipo: " + User.class.getName()
                        ));

        userModel.setName(request.getName());
        userModel.setEmail(request.getEmail());
        userModel.setPassword(request.getPassword());

        userRepository.save(userModel);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}