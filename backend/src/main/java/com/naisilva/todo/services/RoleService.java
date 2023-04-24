package com.naisilva.todo.services;

import com.naisilva.todo.config.enums.RoleName;
import com.naisilva.todo.domain.RoleEntity;
import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.dtos.roleDtos.CreateUserRoleDto;
import com.naisilva.todo.dtos.roleDtos.RoleDtoRequest;
import com.naisilva.todo.exceptions.ObjectNotFoundException;
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

    public UserEntity execute(CreateUserRoleDto createUserRoleDto) {

        Optional<UserEntity> userExists = userRepository.findById(createUserRoleDto.getUserId());

        if (userExists.isEmpty()) {
            throw new RuntimeException("Usuario Não existe!");
        }

        List<RoleEntity> roles = createUserRoleDto.getIdsRoles().stream()
                .map(
                        role -> {
                            return new RoleEntity(role);
                        }
                ).collect(Collectors.toList());

        UserEntity user = userExists.get();

        user.setRoles(roles);

        userRepository.save(user);

        return user;
    }

    public RoleEntity createRole(RoleDtoRequest request) {
        RoleEntity role = new RoleEntity();
        RoleName roleName = RoleName.valueOf(request.getName().toUpperCase());
        role.setName(roleName);
        return roleRepository.save(role);
    }

    public List<RoleEntity> listAllRoles() {
        return roleRepository.findAll();
    }

    public RoleEntity findById(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(
                        "Role não encontrada id: " + id + ", tipo: " + RoleEntity.class.getName()
                ));
    }
}
