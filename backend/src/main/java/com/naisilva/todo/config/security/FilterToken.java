package com.naisilva.todo.config.security;

import com.naisilva.todo.exceptions.ObjectNotFoundException;
import com.naisilva.todo.repositories.UserRepository;
import com.naisilva.todo.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token;

        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null){
            token = authorizationHeader.replace("Bearer ","");
            var subject = this.tokenService.getSubject(token);

            var user = this.userRepository.findByName(subject).orElseThrow(
                    () -> new ObjectNotFoundException(
                            "Role não encontrada! "
                    ));

            var authentication = new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);

    }

}
