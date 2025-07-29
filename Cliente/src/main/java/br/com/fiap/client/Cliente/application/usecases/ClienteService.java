package br.com.fiap.client.Cliente.application.usecases;


import br.com.fiap.client.Cliente.domain.entities.Cliente;
import br.com.fiap.client.Cliente.interfaces.gateways.ClienteGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteGateway clienteGateway;

    public ClienteService(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public Cliente salvar(Cliente cliente) {
        return clienteGateway.salvar(cliente);
    }

    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteGateway.buscarPorId(id);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteGateway.buscarPorCpf(cpf);
    }

    public List<Cliente> listarTodos() {
        return clienteGateway.listarTodos();
    }

    public Cliente atualizar(UUID id, Cliente clienteAtualizado) {
        return clienteGateway.atualizar(id, clienteAtualizado);
    }

    public void deletar(UUID id) {
        clienteGateway.deletar(id);
    }
}