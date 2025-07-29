package br.com.fiap.Pedido.infrastructure.controllers.dto;

public record EnderecoResponse(
        String rua,
        String numero,
        String cep
) {}