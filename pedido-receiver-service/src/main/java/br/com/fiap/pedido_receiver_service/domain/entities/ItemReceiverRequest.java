package br.com.fiap.pedido_receiver_service.domain.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ItemReceiverRequest(
        @NotBlank String sku,
        @Min(1) int quantidade
) {}