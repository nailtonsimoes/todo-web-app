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

    @Autowired
    public UserService(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                () -> new ObjectNotFoundException(
                        "item não encontrado id: " + id + ", tipo: " + User.class.getName()
                ));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "Usuario não encontrado: " + email + ", tipo: " + User.class.getName()
                        ));
    }

    public Optional<User> getUserByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void updateUser(Long id, User user) {
        User userModel = userRepository.findById(id)
                .orElseThrow(
                        () -> new ObjectNotFoundException(
                                "Usuario não encontrado id: " + id + ", tipo: " + User.class.getName()
                        ));

        userModel.setUserName(user.getUserName());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        userModel.setToken(user.getToken());

        userRepository.save(userModel);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}