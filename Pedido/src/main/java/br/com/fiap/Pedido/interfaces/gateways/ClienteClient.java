package br.com.fiap.Pedido.interfaces.gateways;

import br.com.fiap.Pedido.infrastructure.controllers.dto.ClienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "cliente-service", url = "http://localhost:8080")
public interface ClienteClient {

    @GetMapping("/clientes/{id}")
    ClienteResponse buscarPorId(@PathVariable UUID id);
}
