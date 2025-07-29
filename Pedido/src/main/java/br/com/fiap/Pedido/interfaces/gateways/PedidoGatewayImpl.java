package br.com.fiap.Pedido.interfaces.gateways;

import br.com.fiap.Pedido.domain.entities.Pedido;
import br.com.fiap.Pedido.infrastructure.persistence.PedidoMapper;
import br.com.fiap.Pedido.infrastructure.persistence.PedidoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PedidoGatewayImpl implements PedidoGateway {

    private final PedidoRepository repository;

    public PedidoGatewayImpl(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return PedidoMapper.toDomain(repository.save(PedidoMapper.toEntity(pedido)));
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        return repository.findById(id).map(PedidoMapper::toDomain);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(PedidoMapper::toDomain)
                .toList();
    }

    @Override
    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}