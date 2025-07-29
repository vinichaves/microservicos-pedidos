package br.com.fiap.client.Cliente.interfaces.gateways;

import br.com.fiap.client.Cliente.domain.entities.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteGateway {

    Cliente salvar(Cliente cliente);

    Optional<Cliente> buscarPorId(UUID id);

    Optional<Cliente> buscarPorCpf(String cpf);

    List<Cliente> listarTodos();

    Cliente atualizar(UUID id, Cliente cliente);

    void deletar(UUID id);
}
