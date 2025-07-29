package br.com.fiap.Pedido.application.usecases;

import br.com.fiap.Pedido.domain.entities.ItemPedido;
import br.com.fiap.Pedido.domain.entities.Pedido;
import br.com.fiap.Pedido.domain.entities.StatusPedido;
import br.com.fiap.Pedido.infrastructure.Producer.PedidoProducer;
import br.com.fiap.Pedido.infrastructure.controllers.dto.ItemPedidoRequest;
import br.com.fiap.Pedido.infrastructure.controllers.dto.PagamentoRequest;
import br.com.fiap.Pedido.infrastructure.controllers.dto.PagamentoResponse;
import br.com.fiap.Pedido.interfaces.gateways.*;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoGateway pedidoGateway;
    private final ProdutoClient produtoClient;
    private final ClienteClient clienteClient;
    private final PedidoProducer pedidoProducer;

    public Pedido criarPedido(UUID clienteId, List<ItemPedidoRequest> itensRequest, String numeroCartao) {

        // 1. Valida cliente
        try {
            clienteClient.buscarPorId(clienteId);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Cliente não encontrado");
        }

        // 2. Mapeia itens
        List<ItemPedido> itens = itensRequest.stream()
                .map(i -> new ItemPedido(i.sku(), i.quantidade()))
                .collect(Collectors.toList());

        // 3. Calcula total
        BigDecimal total = itens.stream()
                .map(item -> {
                    var produto = produtoClient.buscarPorSku(item.getSku());
                    return produto.preco().multiply(BigDecimal.valueOf(item.getQuantidade()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. Cria pedido com status ABERTO
        Pedido pedido = new Pedido(
                null,
                clienteId,
                itens,
                total,
                StatusPedido.ABERTO
        );
        Pedido salvo = pedidoGateway.salvar(pedido);

        // 5. Envia para a fila
        pedidoProducer.enviarParaFila(salvo, numeroCartao);

        return salvo;
    }

    public List<Pedido> listarTodos() {
        return pedidoGateway.buscarTodos();
    }

    public void excluirPorId(UUID id) {
        pedidoGateway.deletar(id);
    }
}