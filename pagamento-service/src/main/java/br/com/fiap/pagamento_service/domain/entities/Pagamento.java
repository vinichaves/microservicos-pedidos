package br.com.fiap.pagamento_service.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    private UUID id;
    private String numeroCartao;
    private BigDecimal valor;
    private StatusPagamento status;


    public void atualizarStatus(StatusPagamento status) {
        this.status = status;
    }
}