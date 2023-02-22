package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.userDtos.UserDto;
import com.naisilva.todo.services.TodoService;
import com.naisilva.todo.services.UserService;
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

@CrossOrigin("*")
@RestController
@Tag(name="Rotas Usuarios")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final TodoService todoService;

    @Autowired
    public UserController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }


    @PostMapping
    @Operation(summary = "CREATE", description = "Cadastra um usuário")
    @ResponseStatus(CREATED)
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @GetMapping("/all")
    @Operation(summary = "READ ALL USERS", description = "Retorna uma lista de usuários")
    @ResponseStatus(OK)
    public List<UserDto> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "READ BY ID", description = "Retorna um usuário")
    @ResponseStatus(OK)
    public Optional<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserByUserId(id);
    }

    @GetMapping("/{userName}/allTodos/userName")
    @Operation(summary = "READ ALL TODOS BY USERNAME", description = "Retorna uma lista de tarefas baseado em um usuario")
    public ResponseEntity<List<Todo>> getTodosByUsername(@PathVariable String userName) {
        Optional<User> user = userService.getUserByUserName(userName);
        if (user.isPresent()) {
            List<Todo> todos = todoService.getTodosByUserName(user.get().getName());
            return ResponseEntity.ok(todos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/todos/byid")
    @Operation(summary = "READ_All_TODOS_BY_ID", description = "Retorna uma lista de tarefas baseado em um Id de usuario")
    public ResponseEntity<List<Todo>> getTodosById(@PathVariable Long userId) {
        Optional<UserDto> user = userService.getUserByUserId(userId);
        if (user.isPresent()) {
            List<Todo> todos = todoService.getTodosByUserId(user.get().getId());
            return ResponseEntity.ok(todos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "UPDATE", description = "Atualiza um usuario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DELETE", description = "Deleta um usuario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

}


