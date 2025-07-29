package br.com.fiap.pedido_receiver_service.interfaces.gateways;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoque-service", url = "http://localhost:8082")
public interface EstoqueClient {

    @PutMapping("/estoques/{sku}/baixar")
    void baixarEstoque(@PathVariable String sku, @RequestParam int quantidade);
}