package br.com.fiap.Pedido.infrastructure.controllers.dto;

public record PagamentoRequest(
        String pedidoId,
        String numeroCartao,
        Double valor
) {}
