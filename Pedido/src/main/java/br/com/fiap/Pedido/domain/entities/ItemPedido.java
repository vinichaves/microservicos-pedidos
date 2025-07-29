package br.com.fiap.Pedido.domain.entities;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemPedido {
    private String sku;
    private int quantidade;
}