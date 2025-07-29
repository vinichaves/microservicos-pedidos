package br.com.fiap.estoque_service.infrastructure.controllers.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstoqueRequest(
        @NotBlank String produtoSku,
        @NotNull @Min(0) Integer quantidade
) {}