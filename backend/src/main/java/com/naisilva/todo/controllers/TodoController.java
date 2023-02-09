package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.services.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@Tag(name="Rotas Tarefas")
@RequestMapping("api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping(value = "/{id}")
    @Operation(summary = "READ_BY_ID", description = "Retorna uma tarefa")
    public ResponseEntity<Todo> findById(@PathVariable Integer id) {
        Todo todoResponse = todoService.findById(id);
        return ResponseEntity.ok().body(todoResponse);
    }

    @GetMapping(value = "/open")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "READ_ALL_OPEN", description = "Retorna uma lista de tarefas em Aberto")
    public ResponseEntity<List<Todo>> listOpen() {
        List<Todo> list = todoService.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/close")
    @Operation(summary = "READ_ALL_CLOSE", description = "Retorna uma lista de tarefas encerradas")
    public ResponseEntity<List<Todo>> listClose() {
        List<Todo> list = todoService.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "CREATE", description = "Cadastra uma tarefa")
    public ResponseEntity<Todo> insertTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();
        return ResponseEntity.created(uri).body(todo);
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
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        Todo todoModel = todoService.updateTodo(id, todo);
        return ResponseEntity
                .ok()
                .body(todoModel);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.GONE)
    @Operation(summary = "DELETE", description = "Deleta uma tarefa")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
