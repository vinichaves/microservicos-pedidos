package br.com.fiap.client.Cliente.infrastructure.controllers.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(
        @NotBlank String rua,
        @NotBlank String numero,
        @NotBlank String cep
) {}