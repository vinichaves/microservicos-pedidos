package br.com.fiap.client.Cliente.infrastructure.persistence;


import br.com.fiap.client.Cliente.domain.entities.Cliente;
import br.com.fiap.client.Cliente.interfaces.gateways.ClienteGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteRepository clienteRepository;

    public ClienteGatewayImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = ClienteMapper.toEntity(cliente);
        return ClienteMapper.toDomain(clienteRepository.save(entity));
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteRepository.findById(id)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteMapper::toDomain)
                .toList();
    }

    @Override
    public Cliente atualizar(UUID id, Cliente clienteAtualizado) {
        ClienteEntity entity = ClienteMapper.toEntity(clienteAtualizado);
        entity.setId(id);
        return ClienteMapper.toDomain(clienteRepository.save(entity));
    }

    @Override
    public void deletar(UUID id) {
        clienteRepository.deleteById(id);
    }
}