package br.com.fiap.Pedido.infrastructure.controllers.dto;

public record PagamentoResponse(
        String pedidoId,
        String status
) {}