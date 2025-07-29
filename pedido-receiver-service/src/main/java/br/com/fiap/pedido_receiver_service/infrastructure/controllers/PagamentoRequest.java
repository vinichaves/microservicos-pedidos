package br.com.fiap.pedido_receiver_service.infrastructure.controllers;

public record PagamentoRequest(
        String pedidoId,
        String numeroCartao,
        Double valor
) {}
