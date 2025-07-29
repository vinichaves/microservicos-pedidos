package br.com.fiap.pedido_receiver_service.interfaces.gateways;


import br.com.fiap.pedido_receiver_service.domain.entities.PedidoReceiverRequest;
import br.com.fiap.pedido_receiver_service.domain.entities.StatusPedido;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "pedido-service", url = "http://localhost:8083")
public interface PedidoClient {

    @PatchMapping("/pedidos/{id}/status")
    void atualizarStatus(@PathVariable UUID id, @RequestParam StatusPedido status);

    @PostMapping("/pedidos")
    void enviarPedido(@RequestBody PedidoReceiverRequest request);
}