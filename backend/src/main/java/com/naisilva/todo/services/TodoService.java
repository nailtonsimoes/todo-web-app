package com.naisilva.todo.services;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todoRequest) {
        return todoRepository.save(todoRequest);
    }

    public Todo findById(Long id) {
        Optional<Todo> todoResponse = todoRepository.findById(id);
        return todoResponse.orElseThrow(
                () -> new ObjectNotFoundException(
                        "item não encontrado id: " + id + ", tipo: " + Todo.class.getName()
                ));
    }

    public List<Todo> listAll() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Long id, Todo todo) {
        Todo todoModel = findById(id);

        todoModel.setTitle(todo.getTitle());
        todoModel.setFinshed(todo.getFinshed());
        todoModel.setDescription(todo.getDescription());
        todoModel.setDateForFinalize(todo.getDateForFinalize());
        todoModel.setUser(todo.getUser());

        return todoRepository.save(todoModel);
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

    public List<Todo> getTodosByUserId(Long userId) {
        return todoRepository.findByUserId(userId);
    }
}
