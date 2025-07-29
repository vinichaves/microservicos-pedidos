package br.com.fiap.Pedido.interfaces.gateways;

import br.com.fiap.Pedido.infrastructure.config.FeignConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "estoque-service", url = "http://localhost:8082", configuration = FeignConfig.class)
public interface EstoqueClient {

    @PutMapping("/estoques/{sku}/baixar")
    void baixarEstoque(@PathVariable String sku, @RequestParam int quantidade);
}
