package br.com.fiap.client.Cliente.infrastructure.controllers.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public record ClienteRequest(
        @NotBlank String nome,
        @Pattern(regexp = "\\d{11}") String cpf,
        @NotNull LocalDate dataNascimento,
        @NotEmpty List<EnderecoRequest> enderecos
) {}