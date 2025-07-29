package br.com.fiap.Pedido.infrastructure.controllers.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ItemPedidoRequest(
        @NotBlank String sku,
        @Min(1) int quantidade
) {}
