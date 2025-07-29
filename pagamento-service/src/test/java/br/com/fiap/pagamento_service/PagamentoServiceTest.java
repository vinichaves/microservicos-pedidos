package br.com.fiap.pagamento_service;


import br.com.fiap.pagamento_service.application.usecases.PagamentoService;
import br.com.fiap.pagamento_service.domain.entities.Pagamento;
import br.com.fiap.pagamento_service.domain.entities.StatusPagamento;
import br.com.fiap.pagamento_service.interfaces.gateways.PagamentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    private PagamentoGateway gateway;
    private PagamentoService service;

    @BeforeEach
    void setUp() {
        gateway = mock(PagamentoGateway.class);
        service = new PagamentoService(gateway);
    }

    @Test
    void deveAprovarPagamentoValido() {
        String cartao = "4111111111111111";
        BigDecimal valor = BigDecimal.valueOf(150);

        when(gateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Pagamento pagamento = service.processarPagamento(cartao, valor);

        assertEquals(StatusPagamento.APROVADO, pagamento.getStatus());
    }

    @Test
    void deveReprovarPagamentoComFinal0000() {
        String cartao = "4111111111100000";
        BigDecimal valor = BigDecimal.valueOf(200);

        when(gateway.salvar(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Pagamento pagamento = service.processarPagamento(cartao, valor);

        assertEquals(StatusPagamento.REPROVADO, pagamento.getStatus());
    }
}