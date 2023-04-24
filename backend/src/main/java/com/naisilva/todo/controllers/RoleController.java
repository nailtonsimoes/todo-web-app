package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.RoleEntity;
import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.dtos.roleDtos.CreateUserRoleDto;
import com.naisilva.todo.dtos.roleDtos.RoleDtoRequest;
import com.naisilva.todo.services.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Tag(name = "Rotas Roles")
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }


    @PostMapping("/setRole")
    @Operation(summary = "Set a RoleUser", description = "Adiciona uma role a um UserEntity")
    @ResponseStatus(CREATED)
    public UserEntity setRole(@RequestBody CreateUserRoleDto createUserRoleDto) {
        return roleService.execute(createUserRoleDto);
    }

    @PostMapping("/createRole")
    @Operation(summary = "Create a RoleUser", description = "Cadastra uma role")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleEntity createRole(@RequestBody RoleDtoRequest request) {
        return roleService.createRole(request);
    }

    @GetMapping("/allRoles")
    @Operation(summary = "Find All Roles", description = "Busca todos os Roles")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleEntity> findAllRoles () {
        return roleService.listAllRoles();
    }

}
