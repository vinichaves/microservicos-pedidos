package br.com.fiap.pedido_receiver_service.infrastructure.config;

import br.com.fiap.pedido_receiver_service.domain.entities.StatusPedido;
import br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto.ItemPedido;
import br.com.fiap.pedido_receiver_service.infrastructure.controllers.PagamentoRequest;
import br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto.PagamentoResponse;
import br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto.PedidoMessage;
import br.com.fiap.pedido_receiver_service.interfaces.gateways.EstoqueClient;
import br.com.fiap.pedido_receiver_service.interfaces.gateways.PagamentoClient;
import br.com.fiap.pedido_receiver_service.interfaces.gateways.PedidoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoConsumer {

    private final EstoqueClient estoqueClient;
    private final PagamentoClient pagamentoClient;
    private final PedidoClient pedidoClient;

    @RabbitListener(queues = "pedido.queue")
    public void consumir(PedidoMessage message) {
        log.info("📥 Pedido recebido: {}", message.id());

        // Corrigido: uso de record
        for (ItemPedido item : message.itens()) {
            estoqueClient.baixarEstoque(item.sku(), item.quantidade());
        }

        // Corrigido: classe de request correta
        PagamentoRequest pagamentoRequest = new PagamentoRequest(
                message.id().toString(),
                message.numeroCartao(),
                message.total().doubleValue()
        );

        PagamentoResponse pagamento = pagamentoClient.processarPagamento(pagamentoRequest);

        if (!pagamento.status().equalsIgnoreCase("APROVADO")) {
            pedidoClient.atualizarStatus(message.id(), StatusPedido.FECHADO_SEM_CREDITO);
            return;
        }

        pedidoClient.atualizarStatus(message.id(), StatusPedido.FECHADO_COM_SUCESSO);
    }
}