package com.naisilva.todo.services;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.repositories.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Todo createTodo(Todo todoRequest) {
        return todoRepository.save(todoRequest);
    }

    public Todo findById(Integer id) {
        Optional<Todo> todoResponse = todoRepository.findById(id);
        return todoResponse.orElseThrow(
                () -> new ObjectNotFoundException(
                        "item não encontrado id: " + id + ", tipo: " + Todo.class.getName()
                ));
    }

    public List<Todo> listAll() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(Integer id, Todo todo) {
        Todo todoModel = findById(id);

        todoModel.setTitulo(todo.getTitulo());
        todoModel.setFinalizado(todo.getFinalizado());
        todoModel.setDescricao(todo.getDescricao());
        todoModel.setDataParaFinalizar(todo.getDataParaFinalizar());

        return todoRepository.save(todoModel);
    }

    public void deleteTodo(Integer id) {
        todoRepository.deleteById(id);
    }


    public List<Todo> findAllOpen() {
        List<Todo> list = todoRepository.findAllOpen();
        return list;
    }

    public List<Todo> findAllClose() {
        List<Todo> list = todoRepository.findAllClose();
        return list;
    }
}
