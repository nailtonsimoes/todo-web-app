package com.naisilva.todo.services;

import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import com.naisilva.todo.dtos.userDtos.UserDto;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    public Optional<UserDto> getUserByUserId(Long id) {
        Optional<User> userModelOptional = userRepository.findById(id);
        if (userModelOptional.isPresent()) {
            User userModel = userModelOptional.get();
            UserDto user = new UserDto();
            BeanUtils.copyProperties(userModel, user);
            List<TodoDtoResponse> todos = userModel.getTodos().stream()
                    .map(todoModel -> {
                        TodoDtoResponse todo = new TodoDtoResponse();
                        BeanUtils.copyProperties(todoModel, todo);
                        return todo;
                    })
                    .collect(Collectors.toList());
            user.setTodos(todos);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public List<UserDto> listAllUsers() {
        List<User> listUserModel = userRepository.findAll();
        List<UserDto> listUserExit = listUserModel
                .stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUserName(),
                        user.getPassword(),
                        user.getEmail(),
                        user.getToken(),
                        user.getTodos()
                                .stream()
                                .map(
                                        todo -> new TodoDtoResponse(
                                                todo.getId(),
                                                todo.getTitle(),
                                                todo.getDescription(),
                                                todo.getDateForFinalize(),
                                                todo.getFinished()
                                        )
                                ).collect(Collectors.toList()))).collect(Collectors.toList());

        return listUserExit;
    }

    public void updateUser(Long id, User user) {
        User userModel = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "Usuario não encontrado id: " + id + ", tipo: " + User.class.getName()
                        ));

        userModel.setUserName(user.getUserName());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setToken(user.getToken());

        userRepository.save(userModel);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}