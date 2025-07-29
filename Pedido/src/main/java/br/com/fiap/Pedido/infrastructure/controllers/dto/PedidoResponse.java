package br.com.fiap.Pedido.infrastructure.controllers.dto;


import br.com.fiap.Pedido.domain.entities.StatusPedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoResponse(
        UUID id,
        UUID clienteId,
        BigDecimal total,
        StatusPedido status,
        List<ItemPedidoResponse> itens
) {}