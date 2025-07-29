package br.com.fiap.pagamento_service.interfaces.gateways;

import br.com.fiap.pagamento_service.domain.entities.Pagamento;

import java.util.Optional;
import java.util.UUID;

public interface PagamentoGateway {
    Pagamento salvar(Pagamento pagamento);
    Optional<Pagamento> buscarPorId(UUID id);
}
