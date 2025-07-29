package br.com.fiap.estoque_service.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estoques")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class EstoqueEntity {

    @Id
    private String produtoSku;

    @Column(nullable = false)
    private Integer quantidade;
}