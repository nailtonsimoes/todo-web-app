package com.naisilva.todo.dtos.userDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRoleDto {

    private Long userId;

    private List<Long> idsRoles;

}
