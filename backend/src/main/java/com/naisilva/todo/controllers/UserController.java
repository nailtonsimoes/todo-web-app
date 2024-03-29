package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.dtos.TodoResponseDto;
import com.naisilva.todo.dtos.UserRequestDto;
import com.naisilva.todo.dtos.UserResponseDto;
import com.naisilva.todo.services.TodoService;
import com.naisilva.todo.services.RoleService;
import com.naisilva.todo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name="Rotas Usuarios")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final TodoService todoService;

    @Autowired
    public UserController(UserService userService, TodoService todoService, RoleService roleService) {
        this.userService = userService;
        this.todoService = todoService;
    }


    @PostMapping("/create")
    @Operation(summary = "Create a User", description = "Cadastra um usuário")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity createUser(@RequestBody UserRequestDto user) {
        return userService.saveUser(user);
    }


    @GetMapping("/all")
    @Operation(summary = "Find All Users", description = "Retorna uma lista de usuários")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed("ROLE_ADMIN")
    public List<UserResponseDto> getAllUsers() {
        return userService.listAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a USER by Id", description = "Retorna um usuário")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/{userName}/allTodos/userName")
    @Operation(summary = "Find All TODOS by UserName", description = "Retorna uma lista de tarefas baseado em um usuario")
    public ResponseEntity<List<TodoResponseDto>> getTodosByUsername(@PathVariable String userName) {
        Optional<UserEntity> user = userService.findUserByUserName(userName);
        if (user.isPresent()) {
            List<TodoResponseDto> todos = todoService.getTodosByUserName(user.get().getName());
            return ResponseEntity.ok(todos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/allTodos/byUserId")
    @Operation(summary = "Find All TODOS by UserId", description = "Retorna uma lista de tarefas baseado em um Id de usuario")
    public ResponseEntity<List<TodoResponseDto>> getTodosById(@PathVariable Long userId) {
        try{
            UserResponseDto user = userService.findUserById(userId);
            List<TodoResponseDto> todos = todoService.getTodosByUserId(user.getId());
            return ResponseEntity.ok(todos);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a User", description = "Atualiza um usuario")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UserRequestDto user){
        userService.updateUser(id,user);
    }

    @PutMapping("/recover-password/{id}")
    @Operation(summary = "Update a password of a User", description = "Atualiza a senha de um usuario")
    @ResponseStatus(HttpStatus.OK)
    public void updatePasswordUser(@PathVariable Long id, @RequestBody String password){
        userService.updatePassword(id, password);
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a User", description = "Deleta um usuario")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }

}


