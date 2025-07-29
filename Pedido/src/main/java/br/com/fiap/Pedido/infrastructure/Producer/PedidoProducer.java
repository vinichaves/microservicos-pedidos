package br.com.fiap.Pedido.infrastructure.Producer;

import br.com.fiap.Pedido.domain.entities.Pedido;
import br.com.fiap.Pedido.infrastructure.controllers.dto.PedidoMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarParaFila(Pedido pedido, String numeroCartao) {
        PedidoMessage message = new PedidoMessage(
                pedido.getId(),
                pedido.getClienteId(),
                pedido.getItens(),
                pedido.getTotal(),
                numeroCartao
        );
        rabbitTemplate.convertAndSend("pedido.exchange", "pedido.novo", message);
    }
}