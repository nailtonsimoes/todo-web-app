package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.TodoDto;
import com.naisilva.todo.services.TodoService;
import com.naisilva.todo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@Tag(name="Rotas Usuarios")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final TodoService todoService;

    public UserController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @PostMapping
    @Operation(summary = "CREATE", description = "Cria um usuario")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }


    @GetMapping("/{username}")
    @Operation(summary = "READ_All_TODO_BY_USERNAME", description = "Retorna uma lista de tarefas baseado em um usuario")
    public ResponseEntity<List<Todo>> getTodosByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            List<Todo> todos = todoService.getTodosByUserId(user.get().getId());
            return ResponseEntity.ok(todos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{email}/todos")
    public ResponseEntity<List<TodoDto>> findTodosByUserEmail(@PathVariable String email) {
        List<Todo> todos = todoService.findTodosByUserEmail(email);
        ModelMapper modelMapper = new ModelMapper();
        List<TodoDto> todoDtos = todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(todoDtos);
    }
    @GetMapping("/{name}/todos")
    public ResponseEntity<List<TodoDto>> findTodosByUserName(@PathVariable String name) {
        List<Todo> todos = todoService.getTodosByUserName(name);
        ModelMapper modelMapper = new ModelMapper();
        List<TodoDto> todoDtos = todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(todoDtos);
    }
}


