package com.naisilva.todo.dtos.userDtos;


import com.naisilva.todo.dtos.roleDtos.RoleRequestDto;
import com.naisilva.todo.dtos.todoDtos.TodoResponseDto;
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

    private List<RoleRequestDto> roles;

    private String token;

    private List<TodoResponseDto> todos;

}
