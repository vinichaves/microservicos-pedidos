package br.com.fiap.client.Cliente.infrastructure.controllers.dto;

public record EnderecoResponse(
        String rua,
        String numero,
        String cep
) {}