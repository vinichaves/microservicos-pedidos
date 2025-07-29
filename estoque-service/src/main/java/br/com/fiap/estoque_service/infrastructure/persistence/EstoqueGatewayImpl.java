package br.com.fiap.estoque_service.infrastructure.persistence;

import br.com.fiap.estoque_service.domain.entities.Estoque;
import br.com.fiap.estoque_service.interfaces.gateways.EstoqueGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EstoqueGatewayImpl implements EstoqueGateway {

    private final EstoqueRepository repository;

    public EstoqueGatewayImpl(EstoqueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Estoque salvar(Estoque estoque) {
        return EstoqueMapper.toDomain(repository.save(EstoqueMapper.toEntity(estoque)));
    }

    @Override
    public Optional<Estoque> buscarPorSku(String sku) {
        return repository.findById(sku).map(EstoqueMapper::toDomain);
    }

    @Override
    public Estoque atualizar(String sku, Estoque estoque) {
        EstoqueEntity entity = EstoqueMapper.toEntity(estoque);
        entity.setProdutoSku(sku);
        return EstoqueMapper.toDomain(repository.save(entity));
    }

    @Override
    public void baixarEstoque(String sku, int quantidade) {
        Estoque estoque = buscarPorSku(sku)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        estoque.baixar(quantidade);
        salvar(estoque);
    }

    @Override
    public void reporEstoque(String sku, int quantidade) {
        Estoque estoque = buscarPorSku(sku)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        estoque.repor(quantidade);
        salvar(estoque);
    }
}
