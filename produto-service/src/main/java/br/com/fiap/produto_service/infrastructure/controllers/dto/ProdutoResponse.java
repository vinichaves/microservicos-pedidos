package br.com.fiap.produto_service.infrastructure.controllers.dto;


import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponse(
        UUID id,
        String nome,
        String sku,
        BigDecimal preco
) {}