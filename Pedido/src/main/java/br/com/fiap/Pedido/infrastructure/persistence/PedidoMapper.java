package br.com.fiap.Pedido.infrastructure.persistence;

import br.com.fiap.Pedido.domain.entities.ItemPedido;
import br.com.fiap.Pedido.domain.entities.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setClienteId(pedido.getClienteId());
        entity.setStatus(pedido.getStatus());
        entity.setTotal(pedido.getTotal());

        List<ItemPedidoEntity> itens = pedido.getItens().stream()
                .map(i -> new ItemPedidoEntity(null, i.getSku(), i.getQuantidade()))
                .collect(Collectors.toList());

        entity.setItens(itens);
        return entity;
    }

    public static Pedido toDomain(PedidoEntity entity) {
        List<ItemPedido> itens = entity.getItens().stream()
                .map(i -> new ItemPedido(i.getSku(), i.getQuantidade()))
                .collect(Collectors.toList());

        return new Pedido(entity.getId(), entity.getClienteId(), itens, entity.getTotal(), entity.getStatus());
    }
}