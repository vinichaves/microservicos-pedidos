package br.com.fiap.client.Cliente.infrastructure.controllers;


import br.com.fiap.client.Cliente.application.usecases.ClienteService;
import br.com.fiap.client.Cliente.domain.entities.Cliente;
import br.com.fiap.client.Cliente.domain.entities.Endereco;
import br.com.fiap.client.Cliente.infrastructure.controllers.dto.ClienteRequest;
import br.com.fiap.client.Cliente.infrastructure.controllers.dto.ClienteResponse;
import br.com.fiap.client.Cliente.infrastructure.controllers.dto.EnderecoResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> criar(@Valid @RequestBody ClienteRequest request) {
        Cliente cliente = clienteService.salvar(toDomain(request));
        return ResponseEntity.created(URI.create("/clientes/" + cliente.getId()))
                .body(toResponse(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id)
                .map(cliente -> ResponseEntity.ok(toResponse(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar() {
        List<ClienteResponse> clientes = clienteService.listarTodos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@PathVariable UUID id, @Valid @RequestBody ClienteRequest request) {
        Cliente cliente = clienteService.atualizar(id, toDomain(request));
        return ResponseEntity.ok(toResponse(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private Cliente toDomain(ClienteRequest request) {
        List<Endereco> enderecos = request.enderecos().stream()
                .map(e -> new Endereco(e.rua(), e.numero(), e.cep()))
                .collect(Collectors.toList());

        return new Cliente(null, request.nome(), request.cpf(), request.dataNascimento(), enderecos);
    }

    private ClienteResponse toResponse(Cliente cliente) {
        List<EnderecoResponse> enderecos = cliente.getEnderecos().stream()
                .map(e -> new EnderecoResponse(e.getRua(), e.getNumero(), e.getCep()))
                .collect(Collectors.toList());

        return new ClienteResponse(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), enderecos);
    }
}
