package br.com.fiap.pagamento_service.interfaces.gateways;

import br.com.fiap.pagamento_service.domain.entities.Pagamento;
import br.com.fiap.pagamento_service.infrastructure.persistence.PagamentoMapper;
import br.com.fiap.pagamento_service.infrastructure.persistence.PagamentoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final PagamentoRepository repository;

    public PagamentoGatewayImpl(PagamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        return PagamentoMapper.toDomain(repository.save(PagamentoMapper.toEntity(pagamento)));
    }

    @Override
    public Optional<Pagamento> buscarPorId(UUID id) {
        return repository.findById(id).map(PagamentoMapper::toDomain);
    }
}