package br.com.fiap.estoque_service.domain.entities;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {
    private String produtoSku;
    private Integer quantidade;

    public void baixar(int quantidadeBaixada) {
        if (quantidadeBaixada > this.quantidade) {
            throw new IllegalArgumentException("Estoque insuficiente");
        }
        this.quantidade -= quantidadeBaixada;
    }

    public void repor(int quantidadeReposta) {
        this.quantidade += quantidadeReposta;
    }
}