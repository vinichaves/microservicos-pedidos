package br.com.fiap.produto_service;

import br.com.fiap.produto_service.domain.entities.Produto;
import br.com.fiap.produto_service.gateways.ProdutoGateway;
import br.com.fiap.produto_service.usecases.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    private ProdutoGateway gateway;
    private ProdutoService service;

    @BeforeEach
    void setUp() {
        gateway = mock(ProdutoGateway.class);
        service = new ProdutoService(gateway);
    }

    @Test
    void deveSalvarProdutoComSucesso() {
        Produto produto = new Produto(null, "Notebook", "SKU123", new BigDecimal("2999.99"));
        when(gateway.salvar(any())).thenReturn(produto);

        Produto salvo = service.salvar(produto);

        assertNotNull(salvo);
        assertEquals("Notebook", salvo.getNome());
        verify(gateway).salvar(produto);
    }

    @Test
    void deveBuscarProdutoPorId() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Mouse", "SKU999", new BigDecimal("49.90"));
        when(gateway.buscarPorId(id)).thenReturn(Optional.of(produto));

        Optional<Produto> resultado = service.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("Mouse", resultado.get().getNome());
    }

    @Test
    void deveListarTodosOsProdutos() {
        List<Produto> lista = List.of(
                new Produto(UUID.randomUUID(), "Teclado", "SKU1", new BigDecimal("99.90")),
                new Produto(UUID.randomUUID(), "Monitor", "SKU2", new BigDecimal("599.90"))
        );

        when(gateway.listarTodos()).thenReturn(lista);

        List<Produto> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        verify(gateway).listarTodos();
    }

    @Test
    void deveAtualizarProduto() {
        UUID id = UUID.randomUUID();
        Produto atualizado = new Produto(id, "Headset", "SKU987", new BigDecimal("199.99"));
        when(gateway.atualizar(eq(id), any())).thenReturn(atualizado);

        Produto resultado = service.atualizar(id, atualizado);

        assertEquals("Headset", resultado.getNome());
        assertEquals("SKU987", resultado.getSku());
        verify(gateway).atualizar(id, atualizado);
    }

    @Test
    void deveDeletarProdutoPorId() {
        UUID id = UUID.randomUUID();

        service.deletar(id);

        verify(gateway).deletar(id);
    }
}