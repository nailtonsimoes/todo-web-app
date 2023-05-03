package com.naisilva.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;

    private Long roleId;

}
