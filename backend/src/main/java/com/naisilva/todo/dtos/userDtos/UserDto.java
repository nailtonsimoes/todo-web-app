package com.naisilva.todo.dtos.userDtos;


import com.naisilva.todo.dtos.todoDtos.TodoDtoResponse;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String userName;

    private String email;

    private String password;

    private String token;

    private List<TodoDtoResponse> todos;

}
