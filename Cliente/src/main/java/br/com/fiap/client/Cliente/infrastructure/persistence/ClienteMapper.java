package br.com.fiap.client.Cliente.infrastructure.persistence;

import br.com.fiap.client.Cliente.domain.entities.Cliente;
import br.com.fiap.client.Cliente.domain.entities.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteMapper {

    public static ClienteEntity toEntity(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNome(cliente.getNome());
        entity.setCpf(cliente.getCpf());
        entity.setDataNascimento(cliente.getDataNascimento());

        List<EnderecoEntity> enderecoEntities = cliente.getEnderecos().stream()
                .map(end -> new EnderecoEntity(null, end.getRua(), end.getNumero(), end.getCep()))
                .collect(Collectors.toList());

        entity.setEnderecos(enderecoEntities);
        return entity;
    }

    public static Cliente toDomain(ClienteEntity entity) {
        List<Endereco> enderecos = entity.getEnderecos().stream()
                .map(e -> new Endereco(e.getRua(), e.getNumero(), e.getCep()))
                .collect(Collectors.toList());

        return new Cliente(entity.getId(), entity.getNome(), entity.getCpf(), entity.getDataNascimento(), enderecos);
    }
}
