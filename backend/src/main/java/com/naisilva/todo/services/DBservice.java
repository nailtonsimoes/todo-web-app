package com.naisilva.todo.services;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBservice {
    @Autowired
    private  TodoRepository todoRepository;


    public void DbService(TodoRepository repository) {
        this.todoRepository = repository;
    }

    @PostConstruct
    public void instanciaBaseDeDados() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

        Todo t1 = new Todo(null
                ,"Fazer back end"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,true
        );
        Todo t2 = new Todo(null
                ,"Fazer front end"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,false
        );
        Todo t3 = new Todo(null
                ,"Fazer Integração"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,false
        );
        Todo t4 = new Todo(null
                ,"Uma tarefa de teste a mais"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,false
        );

        todoRepository.saveAll(Arrays.asList(t1,t2,t3,t4));
    }
}
