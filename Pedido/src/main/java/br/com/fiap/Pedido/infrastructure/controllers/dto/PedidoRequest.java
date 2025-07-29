package br.com.fiap.Pedido.infrastructure.controllers.dto;

import jakarta.validation.constraints.*;
import java.util.List;
import java.util.UUID;

public record PedidoRequest(
        @NotNull UUID clienteId,
        @NotEmpty List<ItemPedidoRequest> itens,
        @NotBlank String numeroCartao
) {}