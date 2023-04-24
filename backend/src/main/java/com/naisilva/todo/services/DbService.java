package com.naisilva.todo.services;

import com.naisilva.todo.config.enums.RoleName;
import com.naisilva.todo.domain.RoleEntity;
import com.naisilva.todo.domain.TodoEntity;
import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.repositories.RoleRepository;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DbService {

    @Autowired
    private final TodoRepository todoRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    @PersistenceContext
    private final EntityManager em;


    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public DbService(TodoRepository todoRepository, UserRepository userRepository, RoleRepository roleRepository, EntityManager em) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.em = em;
    }

    @PostConstruct
    public void instanciaBaseDeDados() throws ParseException {

        Long count = em.createQuery("SELECT COUNT(u) FROM UserEntity u", Long.class).getSingleResult();

        if (count == 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            UserEntity u1 = new UserEntity(null,
                    "nelson",
                    "nelson@email.com",
                    "123456",
                    "token");

            UserEntity u2 = new UserEntity(
                    null,
                    "osvaldo",
                    "osvaldo@email.com",
                    "123456",
                    "asasas"
            );

            u1.setPassword(passwordEncoder().encode(u1.getPassword()));
            u2.setPassword(passwordEncoder().encode(u2.getPassword()));

            RoleEntity r1 = new RoleEntity(null, RoleName.ROLE_ADMIN);
            roleRepository.save(r1);

            RoleEntity r2 = new RoleEntity(null, RoleName.ROLE_USER);
            roleRepository.save(r2);

            List<RoleEntity> rolesU1 = new ArrayList<>();
            rolesU1.add(r1);

            List<RoleEntity> rolesU2 = new ArrayList<>();
            rolesU2.add(r2);

            u1.setRoles(rolesU1);
            u2.setRoles(rolesU2);

            userRepository.saveAll(Arrays.asList(u1, u2));


            TodoEntity t1 = new TodoEntity(null
                    , "Fazer back end"
                    , "At"
                    , simpleDateFormat.parse("06/02/2023")
                    , true
            );
            t1.setUser(u1);

            TodoEntity t2 = new TodoEntity(null
                    , "Fazer front end"
                    , "At"
                    , simpleDateFormat.parse("06/02/2023")
                    , false
            );
            t2.setUser(u2);

            TodoEntity t3 = new TodoEntity(null
                    , "Fazer Integração"
                    , "At"
                    , simpleDateFormat.parse("06/02/2023")
                    , false
            );
            t3.setUser(u1);

            TodoEntity t4 = new TodoEntity(null
                    , "Uma tarefa de teste a mais"
                    , "At"
                    , simpleDateFormat.parse("06/02/2023")
                    , false
            );
            t4.setUser(u2);

            todoRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
        }
    }
}