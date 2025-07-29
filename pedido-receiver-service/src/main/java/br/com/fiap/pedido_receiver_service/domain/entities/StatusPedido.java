package br.com.fiap.pedido_receiver_service.domain.entities;

public enum StatusPedido {
    ABERTO,
    FECHADO_COM_SUCESSO,
    FECHADO_SEM_ESTOQUE,
    FECHADO_SEM_CREDITO
}