package br.com.fiap.Pedido.interfaces.gateways;

import br.com.fiap.Pedido.infrastructure.config.FeignConfig;
import br.com.fiap.Pedido.infrastructure.controllers.dto.ProdutoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "produto-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface ProdutoClient {

    @GetMapping("/produtos/sku/{sku}")
    ProdutoResponse buscarPorSku(@PathVariable String sku);

}
