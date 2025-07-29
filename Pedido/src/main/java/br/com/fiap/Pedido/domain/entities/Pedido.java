package br.com.fiap.Pedido.domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    private UUID id;
    private UUID clienteId;
    private List<ItemPedido> itens;
    private BigDecimal total;
    private StatusPedido status;


    public void atualizarStatus(StatusPedido status) {
        this.status = status;
    }
}
