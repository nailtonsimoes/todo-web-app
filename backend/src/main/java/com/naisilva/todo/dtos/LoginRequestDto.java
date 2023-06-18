package com.naisilva.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "O campo nome é obrigatório.")
    private String name;
    @NotBlank(message = "O A Senha é obrigatória.")
    private String password;
}
