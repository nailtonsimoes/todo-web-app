package com.naisilva.todo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Todo Web App API", version = "0.1", description = "Aplicação para cadastro de tarefas")
)
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("com.naisilva.todo.controllers")
                .pathsToMatch("/api/**")
                .build();
    }

        /*
         O swagger ja funciona com a classe SwaggerConfig vazia, apenas com as notações
         OpenApiDefinition e Info, e ao mesmo tempo funciona se deixar apenas as notações
         Configuration e Bean junto a classe publicApi.
        */
}
