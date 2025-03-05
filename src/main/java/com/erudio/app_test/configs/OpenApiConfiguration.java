package com.erudio.app_test.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Api de Testes Unitários",
                description = "Api feita para utilizar e treinar conceitos sobre testes unitários.",
                version = "v1",
                contact = @Contact(
                        name = "Marcos André",
                        email = "marcosdev2002@gmail.com"
                )

        )
)
public class OpenApiConfiguration {
}
