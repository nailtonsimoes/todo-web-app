package com.naisilva.todo.controllers;

import com.naisilva.todo.domain.UserEntity;
import com.naisilva.todo.dtos.LoginRequestDto;
import com.naisilva.todo.dtos.LoginResponseDto;
import com.naisilva.todo.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Rotas Login")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Authentication route", description = "Responsavel pela autenticação de usuario")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@RequestBody LoginRequestDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword());

        Authentication authenticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var user = (UserEntity) authenticate.getPrincipal();

        user.setToken(tokenService.tokenGenerate(user));

        LoginResponseDto responseDto = new LoginResponseDto();

        responseDto.setUserId(user.getId());
        responseDto.setName(user.getName());
        responseDto.setPassword(user.getPassword());
        responseDto.setEmail(user.getEmail());
        responseDto.setToken(user.getToken());

        return responseDto;
    }
}
