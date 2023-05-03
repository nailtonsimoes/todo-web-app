package com.naisilva.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotNull(message = "O campo nome é obrigatório.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    private String name;

    @NotNull(message = "O campo email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotNull(message = "O campo senha é obrigatório.")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres.")
    private String password;

    private Long roleId;

}
