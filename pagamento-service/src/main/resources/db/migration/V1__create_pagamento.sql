CREATE TABLE pagamentos (
    id UUID PRIMARY KEY,
    numero_cartao VARCHAR(50) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL
);