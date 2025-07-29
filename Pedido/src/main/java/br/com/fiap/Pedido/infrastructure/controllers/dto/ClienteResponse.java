package br.com.fiap.Pedido.infrastructure.controllers.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        List<EnderecoResponse> enderecos
) {}
