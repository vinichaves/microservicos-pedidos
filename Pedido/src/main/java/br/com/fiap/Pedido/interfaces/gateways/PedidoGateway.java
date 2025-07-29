package br.com.fiap.Pedido.interfaces.gateways;

import br.com.fiap.Pedido.domain.entities.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoGateway {
    Pedido salvar(Pedido pedido);
    Optional<Pedido> buscarPorId(UUID id);
    List<Pedido> buscarTodos();
    void deletar(UUID id);
}