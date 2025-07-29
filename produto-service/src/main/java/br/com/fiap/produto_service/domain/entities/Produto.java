package br.com.fiap.produto_service.domain.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private UUID id;
    private String nome;
    private String sku;
    private BigDecimal preco;
}