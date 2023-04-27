package com.naisilva.todo.services;

import com.naisilva.todo.domain.TodoEntity;
import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.dtos.TodoResponseDto;
import com.naisilva.todo.dtos.TodoRequestDto;
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

    public TodoResponseDto createTodo(Long userId, TodoRequestDto request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));

        TodoEntity todoModel = new TodoEntity();

        todoModel.setTitle(request.getTitle());
        todoModel.setDescription(request.getDescription());
        todoModel.setDateForFinalize(request.getDateForFinalize());
        todoModel.setFinished(request.getFinished());
        todoModel.setUser(user);

        todoRepository.save(todoModel);
        TodoResponseDto response = new TodoResponseDto();

        BeanUtils.copyProperties(todoModel, response);
        response.setUserId(userId);

        return response;
    }

    public List<TodoResponseDto> getTodosByUserId(Long userId) {
        List<TodoEntity> listTodosDB = todoRepository.findByUserId(userId);

        List<TodoResponseDto> listTodosResponse = listTodosDB.stream().map(
                todo -> new TodoResponseDto(
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

    public List<TodoResponseDto> getTodosByUserName(String name) {
        List<TodoEntity> listTodosDB = todoRepository.findByUserName(name);

        List<TodoResponseDto> listTodosResponse = listTodosDB.stream().map(
                todo -> new TodoResponseDto(
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

    public List<TodoEntity> findTodosByUserEmail(String email) {
        return todoRepository.findByUserEmail(email);
    }



    public TodoResponseDto findTodoById(Long id) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                "item não encontrado id: " + id + ", tipo: " + TodoEntity.class.getName()
        ));

        TodoResponseDto response = new TodoResponseDto();
        BeanUtils.copyProperties(todo, response);
        response.setUserId(todo.getUser().getId());

        return response;
    }

    public List<TodoEntity> listAll() {
        return todoRepository.findAll();
    }

    public TodoResponseDto updateTodo(Long id, TodoRequestDto todoRequest) {
        TodoEntity todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "Tarefa não encontrado id: " + id + ", tipo: " + TodoEntity.class.getName()
                        ));

        todo.setTitle(todoRequest.getTitle());
        todo.setDescription(todoRequest.getDescription());
        todo.setFinished(todoRequest.getFinished());
        todo.setDateForFinalize(todoRequest.getDateForFinalize());
        todoRepository.save(todo);

        TodoResponseDto response = new TodoResponseDto();
        BeanUtils.copyProperties(todo, response);
        response.setUserId(todo.getUser().getId());

        return response;
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    /*
    public List<TodoEntity> findAllOpen() {
        List<TodoEntity> list = todoRepository.findAllOpen();
        return list;
    }
     */

    /*
    public List<TodoEntity> findAllClose() {
        List<TodoEntity> list = todoRepository.findAllClose();
        return list;
    }
    */

}
