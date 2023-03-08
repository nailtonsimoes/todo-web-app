package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.Todo;
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

    @Operation(summary = "READ_ALL_BY_ID", description = "Retorna uma lista de tarefas baseado em um ID de usuario")
    @GetMapping("/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getTodosByUserId(@PathVariable Long userId) {
        return todoService.getTodosByUserId(userId);
    }

    @Operation(summary = "READ_ALL_BY_USERNAME", description = "Retorna uma lista de tarefas baseado em um nome de usuario")
    @GetMapping("/{userName}/listAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Todo> getTodosByUserUserName(@PathVariable String userName) {
        return todoService.getTodosByUserName(userName);
    }

    @Operation(summary = "READ_BY_ID", description = "Retorna uma tarefa")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Todo getTodoById(@PathVariable Long id) {
        return todoService.findTodoById(id);
    }

    @PostMapping("/{userId}/create")
    @Operation(summary = "CREATE", description = "Cadastra uma tarefa baseada em um usuario")
    @ResponseStatus(CREATED)
    public Todo createTodo(@PathVariable Long userId,@RequestBody TodoDtoResquest todo) {
        return todoService.createTodo(userId,todo);
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
   @PutMapping(value = "/{id}/update")
   @ResponseStatus(HttpStatus.ACCEPTED)
   public Todo updateTodoById(@PathVariable Long id, @RequestBody Todo updatedTodo) {
       return todoService.updateTodo(id, updatedTodo);
   }

    @DeleteMapping(value = "/{id}/delete")
    @ResponseStatus(HttpStatus.GONE)
    @Operation(summary = "DELETE", description = "Deleta uma tarefa")
    public void deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}
