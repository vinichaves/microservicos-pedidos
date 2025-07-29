package br.com.fiap.estoque_service.infrastructure.controllers.dto;

public record EstoqueResponse(
        String produtoSku,
        Integer quantidade
) {}