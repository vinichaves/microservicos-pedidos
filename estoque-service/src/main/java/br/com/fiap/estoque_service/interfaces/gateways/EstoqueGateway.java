package br.com.fiap.estoque_service.interfaces.gateways;

import br.com.fiap.estoque_service.domain.entities.Estoque;

import java.util.Optional;

public interface EstoqueGateway {
    Estoque salvar(Estoque estoque);

    Optional<Estoque> buscarPorSku(String sku);

    Estoque atualizar(String sku, Estoque estoque);

    void baixarEstoque(String sku, int quantidade);

    void reporEstoque(String sku, int quantidade);
}
