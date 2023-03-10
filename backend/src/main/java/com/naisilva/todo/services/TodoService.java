package com.naisilva.todo.services;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import com.naisilva.todo.dtos.todoDtos.TodoDtoResquest;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Todo createTodo(Long userId, TodoDtoResquest todo) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));

        Todo todoModel = new Todo();

        todoModel.setTitle(todo.getTitle());
        todoModel.setDescription(todo.getDescription());
        todoModel.setDateForFinalize(todo.getDateForFinalize());
        todoModel.setFinished(todo.getFinished());
        todoModel.setUser(user);

        return todoRepository.save(todoModel);
    }

    public List<Todo> getTodosByUserId(Long userId) {
        return todoRepository.findByUserId(userId);
    }

    public List<Todo> getTodosByUserName(String name) {
        return todoRepository.findByUserName(name);
    }

    public List<Todo> findTodosByUserEmail(String email) {
        return todoRepository.findByUserEmail(email);
    }



    public Todo findTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "item não encontrado id: " + id + ", tipo: " + Todo.class.getName()
        ));
    }

    public List<Todo> listAll() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Long id, Todo todo) {
        Todo todoModel = findTodoById(id);

        todoModel.setTitle(todo.getTitle());
        todoModel.setDescription(todo.getDescription());
        todoModel.setFinished(todo.getFinished());
        todoModel.setDateForFinalize(todo.getDateForFinalize());

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

}
