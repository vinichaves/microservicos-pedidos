package br.com.fiap.pagamento_service.infrastructure.controllers.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PagamentoRequest(
        String numeroCartao,
        BigDecimal valor
) {}