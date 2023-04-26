package com.naisilva.todo;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@OpenAPIDefinition(info = @Info(title = "Web TodoEntity App API", version = "0.1", description = "Aplicação para cadastro de tarefas"))
@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("morganalinda"));
    }

}
