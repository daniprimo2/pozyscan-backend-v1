package com.gerenciador.frota.aplicacao.config;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "Gerenciador de frota API",
                version = "1.0",
                description = "Documentação da API para o sitema de gerenciamento de frota"
        ),
        tags = {
                @Tag(name = "Gestão de Rh", description = "Controladores de gestao para Recursos Humanos."),
                @Tag(name = "Gestão de Financeiro", description = "Controladores de gestao para para o Gerenciadores financeiros."),
                @Tag(name = "Gestão de Acesso", description = "Controladores de cadastro de usuarios e autenticação."),
                @Tag(name = "Gestão de Integrações", description = "Controladores de consumo de API externas."),
                @Tag(name = "Gestão de Logistica", description = "Controladores de gestao para o setor logistico.")
        }
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAi() {
        return new OpenAPI()
                .info(new Info().title("Gerenciador de frota API")
                        .version("1.0")
                        .description("Documentação da API"));
    }

}
