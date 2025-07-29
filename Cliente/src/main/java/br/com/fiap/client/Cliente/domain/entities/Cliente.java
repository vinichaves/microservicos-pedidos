package br.com.fiap.client.Cliente.domain.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private UUID id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private List<Endereco> enderecos;
}
