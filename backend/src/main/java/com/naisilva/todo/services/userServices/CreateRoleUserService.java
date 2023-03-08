package com.naisilva.todo.services.userServices;

import com.naisilva.todo.domain.Role;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.userDtos.CreateUserRoleDto;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreateRoleUserService {
    @Autowired
    UserRepository userRepository;

    public User execute(CreateUserRoleDto createUserRoleDto){
        Optional<User> userExists = userRepository.findById(createUserRoleDto.getUserId());
        List<Role> roles = new ArrayList<>();

        if(userExists.isEmpty()) {
            throw new RuntimeException("Usuario Não existe!");
        }

        roles = createUserRoleDto.getIdsRoles().stream()
                .map(
                        role -> {
                            return new Role(role);
                        }
                ).collect(Collectors.toList());

        User user = userExists.get();

        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }
}
