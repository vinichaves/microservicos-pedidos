package br.com.fiap.produto_service.infrastructure.controllers.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProdutoRequest(
        @NotBlank String nome,
        @NotBlank String sku,
        @NotNull @DecimalMin("0.01") BigDecimal preco
) {}