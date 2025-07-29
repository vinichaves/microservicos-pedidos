package br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto;


public record ItemPedido(
        String sku,
        int quantidade
) {}
