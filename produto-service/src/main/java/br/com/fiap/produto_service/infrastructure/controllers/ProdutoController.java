package br.com.fiap.produto_service.infrastructure.controllers;

import br.com.fiap.produto_service.domain.entities.Produto;
import br.com.fiap.produto_service.infrastructure.controllers.dto.ProdutoRequest;
import br.com.fiap.produto_service.infrastructure.controllers.dto.ProdutoResponse;
import br.com.fiap.produto_service.usecases.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoRequest request) {
        Produto produto = produtoService.salvar(toDomain(request));
        return ResponseEntity.created(URI.create("/produtos/" + produto.getId()))
                .body(toResponse(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar() {
        List<ProdutoResponse> produtos = produtoService.listarTodos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProdutoResponse> buscarPorSku(@PathVariable String sku) {
        return produtoService.buscarPorSku(sku)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable UUID id) {
        return produtoService.buscarPorId(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody ProdutoRequest request) {
        Produto atualizado = produtoService.atualizar(id, toDomain(request));
        return ResponseEntity.ok(toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private Produto toDomain(ProdutoRequest request) {
        return new Produto(null, request.nome(), request.sku(), request.preco());
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getSku(), produto.getPreco());
    }
}