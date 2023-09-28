package com.naisilva.todo.services;

import com.naisilva.todo.domain.RoleEntity;
import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.dtos.RoleDto;
import com.naisilva.todo.dtos.TodoResponseDto;
import com.naisilva.todo.dtos.UserRequestDto;
import com.naisilva.todo.dtos.UserResponseDto;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.RoleRepository;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, TodoRepository todoRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserEntity saveUser(UserRequestDto request) {
        Optional<UserEntity> existingUser = userRepository.findByName(request.getName());

        if (existingUser.isPresent()) {
            throw new RuntimeException("usuario ja existe");
        }

        if (request.getName().length() < 3) {
            throw new RuntimeException("nome precisar ter no min que 3 caracteres");
        }

        if (request.getPassword().length() < 6) {
            throw new RuntimeException("senha precisar ter no min que 6 caracteres");
        }

        request.setPassword(passwordEncoder().encode(request.getPassword()));

        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(request, user);

        List<RoleEntity> rolesList = new ArrayList<>();
        RoleEntity role = roleRepository.findById(request.getRoleId()).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Role não encontrada! "
                ));
        rolesList.add(role);
        user.setRoles(rolesList);

        return userRepository.save(user);
    }

    public Optional<UserEntity> findUserByUserName(String name) {
        return userRepository.findByName(name);
    }

    public UserResponseDto findUserById(Long id) {

        UserEntity user = userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Usuario não encontrado! "
                ));

        UserResponseDto userResponseDto = new UserResponseDto();

        BeanUtils.copyProperties(user, userResponseDto);

        List<RoleDto> roles = user.getRoles().stream()
                .map(roleModel -> new RoleDto(roleModel.getName().toString())
                ).collect(Collectors.toList());

        userResponseDto.setRoles(roles);

        List<TodoResponseDto> todos = user.getTodos().stream()
                .map(todoModel -> {
                    TodoResponseDto todo = new TodoResponseDto();
                    BeanUtils.copyProperties(todoModel, todo);
                    return todo;
                })
                .collect(Collectors.toList());

        userResponseDto.setTodos(todos);

        return userResponseDto;
    }

    public List<UserResponseDto> listAllUsers() {
        List<UserEntity> listUsersDB = userRepository.findAll();
        return listUsersDB.stream()
                .map(user -> mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public void updateUser(Long id, UserRequestDto request) {
        UserEntity userModel = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "Usuario não encontrado id: " + id + ", tipo: " + UserEntity.class.getName()
                        ));

        userModel.setName(request.getName());
        userModel.setEmail(request.getEmail());
        userModel.setPassword(passwordEncoder().encode(request.getPassword()));

        List<RoleEntity> roleList = new ArrayList<>();

        RoleEntity role = roleRepository.findById(request.getRoleId()).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Role não encontrada id: " + request.getRoleId() + ", tipo: " + RoleEntity.class.getName()
                ));

        roleList.add(role);

        userModel.setRoles(roleList);

        userRepository.save(userModel);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
