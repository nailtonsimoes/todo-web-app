package com.naisilva.todo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.naisilva.todo.domain.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    public String tokenGenerate(UserEntity user) {
        return JWT.create()
                .withIssuer("Todos")
                .withSubject(user.getName())
                .withClaim("id", user.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("itambe"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("itambe"))
                .withIssuer("Todos")
                .build().verify(token).getSubject();
    }

    public Long getUserIdFromToken(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256("itambe"))
                .withIssuer("Todos")
                .build()
                .verify(token);
        return jwt.getClaim("id").asLong();
    }
}
