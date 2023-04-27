package com.naisilva.todo.dtos;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;

    private String name;

    private String password;

    private String email;

    private List<RoleDto> roles;

    private String token;

    private List<TodoResponseDto> todos;

}
