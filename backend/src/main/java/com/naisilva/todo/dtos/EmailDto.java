package com.naisilva.todo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    @NotBlank(message = "Insira o nome do dono")
    private String ownerRef;
    @NotBlank(message = "O Email do destinatario é obrigatório.")
    @Email
    private String emailFrom;
    @NotBlank(message = "O Email de destino é obrigatório.")
    @Email
    private String emailTo;
    @NotBlank(message = "O Titulo do Email é obrigatório.")
    private String subject;
    @NotBlank(message = "Escreva uma menssagem antes de enviar o Email.")
    private String text;
}
