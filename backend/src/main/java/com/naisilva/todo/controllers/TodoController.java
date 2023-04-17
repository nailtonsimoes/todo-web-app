package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResquest;
import com.naisilva.todo.services.TodoService;
import com.naisilva.todo.services.userServices.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@Tag(name = "Rotas Tarefas")
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private final TodoService todoService;

    @Autowired
    private final UserService userService;

    @Autowired
    public TodoController(TodoService todoService, UserService userService) {
        this.todoService = todoService;
        this.userService = userService;
    }

    @Operation(summary = "Find All TODOS by UserId", description = "Retorna uma lista de tarefas baseado em um ID de usuario")
    @GetMapping("/{userId}/listAllByUserId")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDtoResponse> getTodosByUserId(@PathVariable Long userId) {
        return todoService.getTodosByUserId(userId);
    }

    @Operation(summary = "Find All TODOS by UserName", description = "Retorna uma lista de tarefas baseado em um nome de usuario")
    @GetMapping("/{userName}/listAllByUserName")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDtoResponse> getTodosByUserUserName(@PathVariable String userName) {
        return todoService.getTodosByUserName(userName);
    }

    @Operation(summary = "Find a TODO by TodoId", description = "Retorna uma tarefa")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoDtoResponse getTodoById(@PathVariable Long id) {
        return todoService.findTodoById(id);
    }

    @Operation(summary = "Create a TODO", description = "Cadastra uma tarefa baseada em um usuario")
    @PostMapping("/{userId}/create")
    @ResponseStatus(CREATED)
    public TodoDtoResponse createTodo(@PathVariable Long userId, @RequestBody TodoDtoResquest todo) {
        return todoService.createTodo(userId, todo);
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

    @Operation(summary = "Update a TODO", description = "Atualiza uma tarefa")
    @PutMapping(value = "/{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TodoDtoResponse updateTodoById(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        return todoService.updateTodo(id, updatedTodo);
    }

    @Operation(summary = "Delete a TODO by TodoId", description = "Deleta uma tarefa")
    @DeleteMapping(value = "/{id}/delete")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
