//package br.com.fiap.Pedido;
//
//import br.com.fiap.Pedido.application.usecases.PedidoService;
//import br.com.fiap.Pedido.domain.entities.Pedido;
//import br.com.fiap.Pedido.domain.entities.StatusPedido;
//import br.com.fiap.Pedido.infrastructure.controllers.dto.ItemPedidoRequest;
//import br.com.fiap.Pedido.interfaces.gateways.PedidoGateway;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class PedidoServiceTest {
//    private PedidoGateway gateway;
//    private PedidoService service;
//
//    @BeforeEach
//    void setUp() {
//        gateway = mock(PedidoGateway.class);
//        service = new PedidoService(gateway);
//    }
//
//    @Test
//    void deveCriarPedidoComSucesso() {
//        UUID clienteId = UUID.randomUUID();
//        List<ItemPedidoRequest> itens = List.of(new ItemPedidoRequest("ABC123", 2));
//        String cartao = "4111111111111111";
//
//        when(gateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Pedido pedido = service.criarPedido(clienteId, itens, cartao);
//
//        assertEquals(StatusPedido.FECHADO_COM_SUCESSO, pedido.getStatus());
//        assertEquals(new BigDecimal("200"), pedido.getTotal());
//    }
//
//    @Test
//    void deveFalharSeEstoqueIndisponivel() {
//        UUID clienteId = UUID.randomUUID();
//        List<ItemPedidoRequest> itens = List.of(new ItemPedidoRequest("ABC123", 999));
//        String cartao = "4111111111111111";
//
//        when(gateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Pedido pedido = service.criarPedido(clienteId, itens, cartao);
//
//        assertEquals(StatusPedido.FECHADO_SEM_ESTOQUE, pedido.getStatus());
//    }
//
//    @Test
//    void deveFalharSeCartaoInvalido() {
//        UUID clienteId = UUID.randomUUID();
//        List<ItemPedidoRequest> itens = List.of(new ItemPedidoRequest("ABC123", 2));
//        String cartao = "4111111111100000"; // termina com 0000 → mock falha
//
//        when(gateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Pedido pedido = service.criarPedido(clienteId, itens, cartao);
//
//        assertEquals(StatusPedido.FECHADO_SEM_CREDITO, pedido.getStatus());
//    }
//}