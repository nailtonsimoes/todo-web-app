package com.naisilva.todo.services;

import com.naisilva.todo.domain.User;
import com.naisilva.todo.domain.UserPrincipal;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> existingUser = userRepository.findByNameFetchRoles(username);

        if(existingUser.isPresent()){
            throw new RuntimeException("usuario ja existe");
        }

        return UserPrincipal.create(existingUser.get());
    }
}
