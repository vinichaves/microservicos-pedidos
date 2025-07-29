package br.com.fiap.produto_service.infrastructure.persistence;


import br.com.fiap.produto_service.domain.entities.Produto;

public class ProdutoMapper {

    public static ProdutoEntity toEntity(Produto produto) {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(produto.getId());
        entity.setNome(produto.getNome());
        entity.setSku(produto.getSku());
        entity.setPreco(produto.getPreco());
        return entity;
    }

    public static Produto toDomain(ProdutoEntity entity) {
        return new Produto(entity.getId(), entity.getNome(), entity.getSku(), entity.getPreco());
    }
}