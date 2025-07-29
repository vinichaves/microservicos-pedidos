package br.com.fiap.Pedido.infrastructure.controllers.dto;

import br.com.fiap.Pedido.domain.entities.ItemPedido;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

public record PedidoMessage(
        UUID id,
        UUID clienteId,
        List<ItemPedido> itens,
        BigDecimal total,
        String numeroCartao
) {}

