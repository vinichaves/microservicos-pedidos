package br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto;

import br.com.fiap.pedido_receiver_service.application.usecases.PedidoReceiverService;
import br.com.fiap.pedido_receiver_service.domain.entities.PedidoReceiverRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrada-pedidos")
public class PedidoReceiverController {

    private final PedidoReceiverService service;

    public PedidoReceiverController(PedidoReceiverService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> receber(@Valid @RequestBody PedidoReceiverRequest request) {
        service.receberPedido(request);
        return ResponseEntity.accepted().build();
    }
}