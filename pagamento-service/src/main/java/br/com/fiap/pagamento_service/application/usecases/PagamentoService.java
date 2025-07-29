package br.com.fiap.pagamento_service.application.usecases;


import br.com.fiap.pagamento_service.domain.entities.Pagamento;
import br.com.fiap.pagamento_service.domain.entities.StatusPagamento;
import br.com.fiap.pagamento_service.interfaces.gateways.PagamentoGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoService {

    private final PagamentoGateway gateway;

    public PagamentoService(PagamentoGateway gateway) {
        this.gateway = gateway;
    }

    public Pagamento processarPagamento(String numeroCartao, BigDecimal valor) {
        StatusPagamento status = numeroCartao.endsWith("0000") ? StatusPagamento.REPROVADO : StatusPagamento.APROVADO;
        Pagamento pagamento = new Pagamento(UUID.randomUUID(), numeroCartao, valor, status);
        return gateway.salvar(pagamento);
    }

    public Optional<Pagamento> buscarPorId(UUID id) {
        return gateway.buscarPorId(id);
    }
}