package br.com.fiap.pagamento_service.infrastructure.controllers.dto;



import br.com.fiap.pagamento_service.domain.entities.StatusPagamento;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoResponse(
        UUID id,
        String numeroCartao,
        BigDecimal valor,
        StatusPagamento status
) {}