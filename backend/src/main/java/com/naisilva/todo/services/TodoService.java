package com.naisilva.todo.services;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResquest;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository){
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public TodoDtoResponse createTodo(Long userId, TodoDtoResquest todo) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));

        Todo todoModel = new Todo();

        todoModel.setTitle(todo.getTitle());
        todoModel.setDescription(todo.getDescription());
        todoModel.setDateForFinalize(todo.getDateForFinalize());
        todoModel.setFinished(todo.getFinished());
        todoModel.setUser(user);

        todoRepository.save(todoModel);
        TodoDtoResponse response = new TodoDtoResponse();

        BeanUtils.copyProperties(todo, response);

        return response;
    }

    public List<TodoDtoResponse> getTodosByUserId(Long userId) {
        List<Todo> listTodosDB = todoRepository.findByUserId(userId);

        List<TodoDtoResponse> listTodosResponse = listTodosDB.stream().map(
                todo -> new TodoDtoResponse(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.getDateForFinalize(),
                        todo.getFinished(),
                        todo.getUser().getId()
                )
        ).collect(Collectors.toList());
        return listTodosResponse;
    }

    public List<TodoDtoResponse> getTodosByUserName(String name) {
        List<Todo> listTodosDB = todoRepository.findByUserName(name);

        List<TodoDtoResponse> listTodosResponse = listTodosDB.stream().map(
                todo -> new TodoDtoResponse(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.getDateForFinalize(),
                        todo.getFinished(),
                        todo.getUser().getId()
                )
        ).collect(Collectors.toList());
        return listTodosResponse;
    }

    public List<Todo> findTodosByUserEmail(String email) {
        return todoRepository.findByUserEmail(email);
    }



    public TodoDtoResponse findTodoById(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                "item não encontrado id: " + id + ", tipo: " + Todo.class.getName()
        ));

        TodoDtoResponse response = new TodoDtoResponse();
        BeanUtils.copyProperties(todo, response);

        return response;
    }

    public List<Todo> listAll() {
        return todoRepository.findAll();
    }

    public TodoDtoResponse updateTodo(Long id, Todo todoRequest) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "item não encontrado id: " + id + ", tipo: " + Todo.class.getName()
                        ));

        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setFinished(todoRequest.getFinished());
        todo.setDateForFinalize(todoRequest.getDateForFinalize());

        todoRepository.save(todo);
        TodoDtoResponse response = new TodoDtoResponse();
        BeanUtils.copyProperties(todo, response);

        return response;
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    /*
    public List<Todo> findAllOpen() {
        List<Todo> list = todoRepository.findAllOpen();
        return list;
    }
     */

    /*
    public List<Todo> findAllClose() {
        List<Todo> list = todoRepository.findAllClose();
        return list;
    }
    */

}
