package com.naisilva.todo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigModel {
    @Bean
    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
