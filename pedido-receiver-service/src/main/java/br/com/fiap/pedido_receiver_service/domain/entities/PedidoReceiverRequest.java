package br.com.fiap.pedido_receiver_service.domain.entities;

import jakarta.validation.constraints.*;
import java.util.List;
import java.util.UUID;

public record PedidoReceiverRequest(
        @NotNull UUID clienteId,
        @NotEmpty List<ItemReceiverRequest> itens,
        @NotBlank String numeroCartao
) {}