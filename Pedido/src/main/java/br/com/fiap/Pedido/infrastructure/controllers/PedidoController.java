package br.com.fiap.Pedido.infrastructure.controllers;

import br.com.fiap.Pedido.application.usecases.PedidoService;
import br.com.fiap.Pedido.domain.entities.Pedido;
import br.com.fiap.Pedido.infrastructure.controllers.dto.ItemPedidoRequest;
import br.com.fiap.Pedido.infrastructure.controllers.dto.ItemPedidoResponse;
import br.com.fiap.Pedido.infrastructure.controllers.dto.PedidoRequest;
import br.com.fiap.Pedido.infrastructure.controllers.dto.PedidoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> criar(@Valid @RequestBody PedidoRequest request) {
        Pedido pedido = service.criarPedido(request.clienteId(), request.itens(), request.numeroCartao());
        return ResponseEntity.created(URI.create("/pedidos/" + pedido.getId()))
                .body(toResponse(pedido));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarTodos() {
        List<Pedido> pedidos = service.listarTodos();
        List<PedidoResponse> response = pedidos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }

    private PedidoResponse toResponse(Pedido pedido) {
        List<ItemPedidoResponse> itens = pedido.getItens().stream()
                .map(i -> new ItemPedidoResponse(i.getSku(), i.getQuantidade()))
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getTotal(),
                pedido.getStatus(),
                itens
        );
    }
}