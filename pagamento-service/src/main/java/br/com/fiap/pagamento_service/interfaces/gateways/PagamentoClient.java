package br.com.fiap.pagamento_service.interfaces.gateways;

import br.com.fiap.pagamento_service.infrastructure.controllers.dto.PagamentoRequest;
import br.com.fiap.pagamento_service.infrastructure.controllers.dto.PagamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "pagamento-service", url = "http://localhost:8085")
public interface PagamentoClient {

    @PostMapping("/pagamentos")
    PagamentoResponse processarPagamento(@RequestBody PagamentoRequest request);

    @GetMapping("/pagamentos/{id}")
    PagamentoResponse buscarPagamento(@PathVariable UUID id);
}