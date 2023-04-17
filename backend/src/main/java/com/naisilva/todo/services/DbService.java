package com.naisilva.todo.services;

import com.naisilva.todo.domain.Role;
import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.repositories.RoleRepository;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DbService {

    @Autowired
    private final TodoRepository todoRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    public DbService (TodoRepository todoRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void instanciaBaseDeDados() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        User u1 = new User(null,
                "tangonelson",
                "tangonelson@email.com",
                "123456",
                "token");

        User u2 = new User(
                null,
                "osvaldo",
                "osvaldo@email.com",
                "123456",
                "asasas"
                );

        userRepository.saveAll(Arrays.asList(u1, u2));

        Role role1 = new Role(null ,"USER");
        Role role2 = new Role(null ,"ADMIN");

        roleRepository.saveAll(Arrays.asList(role1, role2));

        Todo t1 = new Todo(null
                ,"Fazer back end"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,true
        );
        t1.setUser(u1);

        Todo t2 = new Todo(null
                ,"Fazer front end"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,false
        );
        t2.setUser(u2);

        Todo t3 = new Todo(null
                ,"Fazer Integração"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,false
        );
        t3.setUser(u1);

        Todo t4 = new Todo(null
                ,"Uma tarefa de teste a mais"
                ,"At"
                ,sdf.parse("06/02/2023")
                ,false
        );
        t4.setUser(u2);

        todoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
    }
}