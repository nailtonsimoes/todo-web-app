package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.services.TodoService;
import com.naisilva.todo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@Tag(name="Rotas Tarefas")
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    public TodoController(TodoService todoService, UserService userService){
        this.todoService = todoService;
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "READ_BY_ID", description = "Retorna uma tarefa")
    public ResponseEntity<Todo> findById(@PathVariable Long id) {
        Todo todoResponse = todoService.findById(id);
        return ResponseEntity.ok().body(todoResponse);
    }

    /*
    @GetMapping(value = "/open")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "READ_ALL_OPEN", description = "Retorna uma lista de tarefas em Aberto")
    public ResponseEntity<List<Todo>> listOpen() {
        List<Todo> list = todoService.findAllOpen();
        return ResponseEntity.ok().body(list);
    }
     */

    /*
    @GetMapping(value = "/close")
    @Operation(summary = "READ_ALL_CLOSE", description = "Retorna uma lista de tarefas encerradas")
    public ResponseEntity<List<Todo>> listClose() {
        List<Todo> list = todoService.findAllClose();
        return ResponseEntity.ok().body(list);
    }
     */

    @PostMapping("/{userId}/todos")
    public ResponseEntity<Todo> createTodo(@PathVariable Long userId, @RequestBody Todo todo) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            todo.setUser(user.get());
            Todo createdTodo = todoService.createTodo(todo);
            return ResponseEntity.created(URI.create("/users/" + userId + "/todos/" + createdTodo.getId())).body(createdTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "READ_ALL", description = "Retorna uma lista de tarefas")
    public List<Todo> listAllTodo() {
        return todoService.listAll();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "UPDATE", description = "Atualiza uma tarefa")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        Todo todoModel = todoService.updateTodo(id, todo);
        return ResponseEntity
                .ok()
                .body(todoModel);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.GONE)
    @Operation(summary = "DELETE", description = "Deleta uma tarefa")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
