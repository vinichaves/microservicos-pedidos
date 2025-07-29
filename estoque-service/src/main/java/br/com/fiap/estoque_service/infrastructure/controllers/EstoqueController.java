package br.com.fiap.estoque_service.infrastructure.controllers;

import br.com.fiap.estoque_service.application.usecases.EstoqueService;
import br.com.fiap.estoque_service.domain.entities.Estoque;
import br.com.fiap.estoque_service.infrastructure.controllers.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EstoqueResponse> criar(@Valid @RequestBody EstoqueRequest request) {
        Estoque estoque = service.salvar(toDomain(request));
        return ResponseEntity.created(URI.create("/estoques/" + estoque.getProdutoSku()))
                .body(toResponse(estoque));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<EstoqueResponse> buscarPorSku(@PathVariable String sku) {
        return service.buscarPorSku(sku)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{sku}")
    public ResponseEntity<EstoqueResponse> atualizar(@PathVariable String sku, @Valid @RequestBody EstoqueRequest request) {
        Estoque atualizado = service.atualizar(sku, toDomain(request));
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @PutMapping("/{sku}/baixar")
    public ResponseEntity<Void> baixar(@PathVariable String sku, @RequestParam int quantidade) {
        service.baixar(sku, quantidade);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{sku}/repor")
    public ResponseEntity<Void> repor(@PathVariable String sku, @RequestParam int quantidade) {
        service.repor(sku, quantidade);
        return ResponseEntity.noContent().build();
    }

    private Estoque toDomain(EstoqueRequest request) {
        return new Estoque(request.produtoSku(), request.quantidade());
    }

    private EstoqueResponse toResponse(Estoque estoque) {
        return new EstoqueResponse(estoque.getProdutoSku(), estoque.getQuantidade());
    }
}
