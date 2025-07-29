package br.com.fiap.pedido_receiver_service.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Pedido Receiver Service API",
                version = "1.0",
                description = "Documentação dos endpoints de recebimento de pedidos"
        )
)
public class SwaggerConfig {}