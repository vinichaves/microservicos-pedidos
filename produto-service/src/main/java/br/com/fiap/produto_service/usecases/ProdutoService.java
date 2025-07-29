package br.com.fiap.produto_service.usecases;

import br.com.fiap.produto_service.domain.entities.Produto;
import br.com.fiap.produto_service.gateways.ProdutoGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoGateway produtoGateway;

    public ProdutoService(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto salvar(Produto produto) {
        return produtoGateway.salvar(produto);
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return produtoGateway.buscarPorId(id);
    }

    public Optional<Produto> buscarPorSku(String sku) {
        return produtoGateway.buscarPorSku(sku);
    }

    public List<Produto> listarTodos() {
        return produtoGateway.listarTodos();
    }

    public Produto atualizar(UUID id, Produto produtoAtualizado) {
        return produtoGateway.atualizar(id, produtoAtualizado);
    }

    public void deletar(UUID id) {
        produtoGateway.deletar(id);
    }
}