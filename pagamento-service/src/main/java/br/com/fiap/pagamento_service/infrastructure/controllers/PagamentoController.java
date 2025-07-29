package br.com.fiap.pagamento_service.infrastructure.controllers;

import br.com.fiap.pagamento_service.application.usecases.PagamentoService;
import br.com.fiap.pagamento_service.domain.entities.Pagamento;
import br.com.fiap.pagamento_service.infrastructure.controllers.dto.PagamentoRequest;
import br.com.fiap.pagamento_service.infrastructure.controllers.dto.PagamentoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> pagar(@Valid @RequestBody PagamentoRequest request) {
        Pagamento pagamento = service.processarPagamento(request.numeroCartao(), request.valor());
        return ResponseEntity.created(URI.create("/pagamentos/" + pagamento.getId()))
                .body(toResponse(pagamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscar(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private PagamentoResponse toResponse(Pagamento pagamento) {
        return new PagamentoResponse(
                pagamento.getId(),
                pagamento.getNumeroCartao(),
                pagamento.getValor(),
                pagamento.getStatus()
        );
    }
}