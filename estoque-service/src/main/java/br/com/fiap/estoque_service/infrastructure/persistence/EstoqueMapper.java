package br.com.fiap.estoque_service.infrastructure.persistence;


import br.com.fiap.estoque_service.domain.entities.Estoque;

public class EstoqueMapper {

    public static EstoqueEntity toEntity(Estoque estoque) {
        return new EstoqueEntity(estoque.getProdutoSku(), estoque.getQuantidade());
    }

    public static Estoque toDomain(EstoqueEntity entity) {
        return new Estoque(entity.getProdutoSku(), entity.getQuantidade());
    }
}