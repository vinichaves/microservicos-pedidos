CREATE TABLE clientes (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL
);

CREATE TABLE enderecos (
    id BIGSERIAL PRIMARY KEY,
    rua VARCHAR(100),
    numero VARCHAR(20),
    cep VARCHAR(9),
    cliente_id UUID REFERENCES clientes(id) ON DELETE CASCADE
);
