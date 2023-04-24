package com.naisilva.todo.config.security;

import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario nao encontrado: " + username));
        return new User(user.getUsername(),user.getPassword(),true,true,true,true,user.getAuthorities());
    }
}

