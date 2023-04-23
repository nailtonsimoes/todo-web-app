package com.naisilva.todo.services;

import com.naisilva.todo.config.enums.RoleName;
import com.naisilva.todo.domain.Role;
import com.naisilva.todo.domain.User;
import com.naisilva.todo.dtos.roleDtos.CreateUserRoleDto;
import com.naisilva.todo.dtos.roleDtos.RoleDtoRequest;
import com.naisilva.todo.repositories.RoleRepository;
import com.naisilva.todo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public User execute(CreateUserRoleDto createUserRoleDto){

        Optional<User> userExists = userRepository.findById(createUserRoleDto.getUserId());

        if(userExists.isEmpty()) {
            throw new RuntimeException("Usuario Não existe!");
        }

        List<Role> roles = createUserRoleDto.getIdsRoles().stream()
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

    public Role createRole(RoleDtoRequest request) {
        Role role = new Role();
        RoleName roleName = RoleName.valueOf(request.getName().toUpperCase());
        role.setName(roleName);
        return roleRepository.save(role);
    }

    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }
}
