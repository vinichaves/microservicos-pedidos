package br.com.fiap.pedido_receiver_service;

import br.com.fiap.pedido_receiver_service.infrastructure.controllers.dto.PedidoReceiverController;
import br.com.fiap.pedido_receiver_service.application.usecases.PedidoReceiverService;
import br.com.fiap.pedido_receiver_service.domain.entities.ItemReceiverRequest;
import br.com.fiap.pedido_receiver_service.domain.entities.PedidoReceiverRequest;
import br.com.fiap.pedido_receiver_service.interfaces.gateways.PedidoClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoReceiverController.class)
public class PedidoReceiverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private PedidoClient pedidoClient;

    @MockitoBean
    private PedidoReceiverService pedidoReceiverService;

    @Test
    void deveAceitarPedidoComDadosValidos() throws Exception {
        PedidoReceiverRequest request = new PedidoReceiverRequest(
                UUID.randomUUID(),
                List.of(new ItemReceiverRequest("PROD123", 2)),
                "1234567890123456"
        );

        mockMvc.perform(post("/entrada-pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());
    }

    @Test
    void deveRetornar400ComCartaoInvalido() throws Exception {
        PedidoReceiverRequest request = new PedidoReceiverRequest(
                UUID.randomUUID(),
                List.of(new ItemReceiverRequest("PROD123", 1)),
                "" // Cartão inválido
        );

        mockMvc.perform(post("/entrada-pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveCriarPedidoReceiverRequestValido() {
        PedidoReceiverRequest request = new PedidoReceiverRequest(
                UUID.randomUUID(),
                List.of(new ItemReceiverRequest("PROD001", 2)),
                "1234567890123456"
        );
        assertEquals("PROD001", request.itens().get(0).sku());
        assertEquals(2, request.itens().get(0).quantidade());
    }

    @Test
    void deveCriarItemReceiverComDadosValidos() {
        ItemReceiverRequest item = new ItemReceiverRequest("PROD001", 3);
        assertEquals("PROD001", item.sku());
        assertEquals(3, item.quantidade());
    }
}