package br.com.fiap.pedido_receiver_service.interfaces.gateways;


import br.com.fiap.pedido_receiver_service.infrastructure.controllers.PagamentoRequest;
import br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto.PagamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamento-service", url = "http://localhost:8084")
public interface PagamentoClient {

    @PostMapping("/pagamentos")
    PagamentoResponse processarPagamento(@RequestBody PagamentoRequest request);
}