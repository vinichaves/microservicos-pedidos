package br.com.fiap.pedido_receiver_service.application.usecases;

import br.com.fiap.pedido_receiver_service.domain.entities.PedidoReceiverRequest;
import br.com.fiap.pedido_receiver_service.interfaces.gateways.PedidoClient;
import org.springframework.stereotype.Service;

@Service
public class PedidoReceiverService {

    private final PedidoClient pedidoClient;

    public PedidoReceiverService(PedidoClient pedidoClient) {
        this.pedidoClient = pedidoClient;
    }

    public void receberPedido(PedidoReceiverRequest request) {
        pedidoClient.enviarPedido(request);
    }
}