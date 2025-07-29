package br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoMessage(
        UUID id,
        UUID clienteId,
        List<ItemPedido> itens,
        BigDecimal total,
        String numeroCartao
) implements Serializable {}