package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.EmailEntity;
import com.naisilva.todo.dtos.EmailDto;
import com.naisilva.todo.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name="Rotas Email")
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    @Operation(summary = "Send a Email", description = "Envia um email para o usuário")
    public ResponseEntity<EmailEntity> sendingEmail(@RequestBody @Valid EmailDto emailDto){
        EmailEntity emailEntity = new EmailEntity();
        BeanUtils.copyProperties(emailDto,emailEntity);
        emailService.sendEmail(emailEntity);
        return  new ResponseEntity<>(emailEntity, HttpStatus.CREATED);
    }
}