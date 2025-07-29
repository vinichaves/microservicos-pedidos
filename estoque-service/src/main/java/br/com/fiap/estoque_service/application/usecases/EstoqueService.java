package br.com.fiap.estoque_service.application.usecases;

import br.com.fiap.estoque_service.domain.entities.Estoque;
import br.com.fiap.estoque_service.interfaces.gateways.EstoqueGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {

    private final EstoqueGateway gateway;

    public EstoqueService(EstoqueGateway gateway) {
        this.gateway = gateway;
    }

    public Estoque salvar(Estoque estoque) {
        return gateway.salvar(estoque);
    }

    public Optional<Estoque> buscarPorSku(String sku) {
        return gateway.buscarPorSku(sku);
    }

    public Estoque atualizar(String sku, Estoque estoque) {
        return gateway.atualizar(sku, estoque);
    }

    public void baixar(String sku, int quantidade) {
        gateway.baixarEstoque(sku, quantidade);
    }

    public void repor(String sku, int quantidade) {
        gateway.reporEstoque(sku, quantidade);
    }
}