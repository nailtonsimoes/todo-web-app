package com.naisilva.todo.services;

import com.naisilva.todo.domain.Todo;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.TodoRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    public UserService(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public User findById(Long id) {
        Optional<User> userResponse = userRepository.findById(id);
        return userResponse.orElseThrow(
                () -> new ObjectNotFoundException(
                        "item não encontrado id: " + id + ", tipo: " + User.class.getName()
                ));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User updateUser(User existingUser, User newUser) {
        existingUser.setUserName(newUser.getUserName());
        existingUser.setPassword(newUser.getPassword());
        // atualizar outros atributos do usuário, se necessário

        // atualizar a lista de Todos
        List<Todo> existingTodos = existingUser.getTodos();
        List<Todo> newTodos = newUser.getTodos();
        List<Todo> todosToRemove = new ArrayList<>();

        // percorrer a lista de Todos existentes e verificar quais tarefas foram removidas
        for (Todo existingTodo : existingTodos) {
            if (!newTodos.contains(existingTodo)) {
                todosToRemove.add(existingTodo);
                existingTodo.setUser(null); // remover a associação com o User
            }
        }

        // remover as tarefas que foram removidas
        todoRepository.deleteAll(todosToRemove);

        // percorrer a lista de Todos recebida na requisição e verificar quais foram atualizadas ou adicionadas
        for (Todo newTodo : newTodos) {
            Optional<Todo> optionalExistingTodo = todoRepository.findById(newTodo.getId());
            if (optionalExistingTodo.isPresent()) {
                Todo existingTodo = optionalExistingTodo.get();
                existingTodo.setTitle(newTodo.getTitle());
                existingTodo.setDescription(newTodo.getDescription());
                // atualizar outros atributos da tarefa, se necessário
                todoRepository.save(existingTodo); // atualizar a tarefa existente
            } else {
                newTodo.setUser(existingUser);
                todoRepository.save(newTodo); // adicionar a nova tarefa ao banco de dados
            }
        }

        return userRepository.save(existingUser); // salvar as mudanças no User
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}