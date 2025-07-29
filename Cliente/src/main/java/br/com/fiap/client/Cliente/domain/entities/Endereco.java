package br.com.fiap.client.Cliente.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String rua;
    private String numero;
    private String cep;
}
