package br.com.fiap.produto_service.infrastructure.persistence;

import br.com.fiap.produto_service.domain.entities.Produto;
import br.com.fiap.produto_service.gateways.ProdutoGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoRepository repository;

    public ProdutoGatewayImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity entity = ProdutoMapper.toEntity(produto);
        return ProdutoMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Produto> buscarPorId(UUID id) {
        return repository.findById(id).map(ProdutoMapper::toDomain);
    }

    @Override
    public Optional<Produto> buscarPorSku(String sku) {
        return repository.findBySku(sku).map(ProdutoMapper::toDomain);
    }

    @Override
    public List<Produto> listarTodos() {
        return repository.findAll().stream().map(ProdutoMapper::toDomain).toList();
    }

    @Override
    public Produto atualizar(UUID id, Produto produto) {
        ProdutoEntity entity = ProdutoMapper.toEntity(produto);
        entity.setId(id);
        return ProdutoMapper.toDomain(repository.save(entity));
    }

    @Override
    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}