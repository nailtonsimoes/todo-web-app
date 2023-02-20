package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.services.TodoService;
import com.naisilva.todo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Tag(name="Rotas Tarefas")
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private final TodoService todoService;

    @Autowired
    private final UserService userService;

    @Autowired
    public TodoController(TodoService todoService, UserService userService){
        this.todoService = todoService;
        this.userService = userService;
    }

    @Operation(summary = "READ_ALL_BY_ID", description = "Retorna uma lista de tarefas baseado em um usuario")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getTodosByUserId(@PathVariable Long userId) {
        return todoService.getTodosByUserId(userId);
    }

    @Operation(summary = "READ_BY_ID", description = "Retorna uma tarefa")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.findTodoById(id);
    }

    @Operation(summary = "CREATE", description = "Cria uma tarefa")
    @PostMapping("/{userId}/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@PathVariable Long userId, @RequestBody Todo todo) {
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

   @Operation(summary = "UPDATE", description = "Atualiza uma tarefa")
   @PutMapping(value = "/{id}")
   @ResponseStatus(HttpStatus.ACCEPTED)
   public Todo updateTodoById(@PathVariable Long id, @RequestBody Todo updatedTodo) {
       return todoService.updateTodo(id, updatedTodo);
   }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.GONE)
    @Operation(summary = "DELETE", description = "Deleta uma tarefa")
    public void deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
