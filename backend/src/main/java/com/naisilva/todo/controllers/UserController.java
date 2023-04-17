package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import com.naisilva.todo.dtos.userDtos.CreateUserRoleDto;
import com.naisilva.todo.dtos.userDtos.UserRequestDto;
import com.naisilva.todo.dtos.userDtos.UserResponseDto;
import com.naisilva.todo.services.TodoService;
import com.naisilva.todo.services.userServices.CreateRoleUserService;
import com.naisilva.todo.services.userServices.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name="Rotas Usuarios")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final TodoService todoService;

    @Autowired
    private final CreateRoleUserService createRoleUserService;

    @Autowired
    public UserController(UserService userService, TodoService todoService, CreateRoleUserService createRoleUserService) {
        this.userService = userService;
        this.todoService = todoService;
        this.createRoleUserService = createRoleUserService;
    }


    @PostMapping("/create")
    @Operation(summary = "Create a User", description = "Cadastra um usuário")
    @ResponseStatus(CREATED)
    public User createUser(@RequestBody UserRequestDto user) {
        return userService.saveUser(user);
    }

    @PostMapping("/role")
    @Operation(summary = "Create a Role", description = "Cadastra uma role")
    @ResponseStatus(CREATED)
    public User role(@RequestBody CreateUserRoleDto createUserRoleDto) {
        return createRoleUserService.execute(createUserRoleDto);
    }

    @GetMapping("/all")
    @Operation(summary = "Find All Users", description = "Retorna uma lista de usuários")
    @ResponseStatus(OK)
    public List<UserResponseDto> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a USER by Id", description = "Retorna um usuário")
    @ResponseStatus(OK)
    public Optional<UserResponseDto> getUserById(@PathVariable Long id) {
        return userService.getUserByUserId(id);
    }

    @GetMapping("/{userName}/allTodos/userName")
    @Operation(summary = "Find All TODOS by UserName", description = "Retorna uma lista de tarefas baseado em um usuario")
    public ResponseEntity<List<TodoDtoResponse>> getTodosByUsername(@PathVariable String userName) {
        Optional<User> user = userService.getUserByUserName(userName);
        if (user.isPresent()) {
            List<TodoDtoResponse> todos = todoService.getTodosByUserName(user.get().getName());
            return ResponseEntity.ok(todos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/allTodos/byUserId")
    @Operation(summary = "Find All TODOS by UserId", description = "Retorna uma lista de tarefas baseado em um Id de usuario")
    public ResponseEntity<List<TodoDtoResponse>> getTodosById(@PathVariable Long userId) {
        Optional<UserResponseDto> user = userService.getUserByUserId(userId);
        if (user.isPresent()) {
            List<TodoDtoResponse> todos = todoService.getTodosByUserId(user.get().getId());
            return ResponseEntity.ok(todos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a User", description = "Atualiza um usuario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id, @RequestBody UserRequestDto user){
        userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a User", description = "Deleta um usuario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

}


