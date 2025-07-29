package br.com.fiap.pagamento_service.infrastructure.persistence;

import br.com.fiap.pagamento_service.domain.entities.StatusPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PagamentoEntity {

    @Id
    private UUID id;

    private String numeroCartao;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
}