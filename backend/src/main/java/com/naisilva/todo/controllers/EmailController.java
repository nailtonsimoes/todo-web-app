package com.naisilva.todo.controllers;

import com.naisilva.todo.services.EmailService;
import com.naisilva.todo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name="Rotas Email")
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @PostMapping("/forgot-password")
    @Operation(summary = "recover password by email", description = "Envia um email para o usuário com o link de recuperação de senha")
    @ResponseStatus(HttpStatus.OK)
    public void sendingEmail(@RequestBody @Valid String email){
        emailService.forgotPassword(email);
    }
}