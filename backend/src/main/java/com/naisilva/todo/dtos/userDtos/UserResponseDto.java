package com.naisilva.todo.dtos.userDtos;


import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;

    private String name;

    private String email;

    private String password;

    private String token;

    private List<TodoDtoResponse> todos;

}
