package br.com.fiap.produto_service.gateways;


import br.com.fiap.produto_service.domain.entities.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoGateway {
    Produto salvar(Produto produto);

    Optional<Produto> buscarPorId(UUID id);

    Optional<Produto> buscarPorSku(String sku);

    List<Produto> listarTodos();

    Produto atualizar(UUID id, Produto produtoAtualizado);

    void deletar(UUID id);
}