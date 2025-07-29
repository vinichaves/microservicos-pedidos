package br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto;

public record PagamentoResponse(
        String pedidoId,
        String status
) {}
