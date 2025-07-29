package br.com.fiap.pedido_receiver_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PedidoReceiverServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoReceiverServiceApplication.class, args);
	}

}
