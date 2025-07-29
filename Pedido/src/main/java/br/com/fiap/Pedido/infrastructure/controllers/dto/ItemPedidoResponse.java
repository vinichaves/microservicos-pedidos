package br.com.fiap.Pedido.infrastructure.controllers.dto;

public record ItemPedidoResponse(
        String sku,
        int quantidade
) {}
