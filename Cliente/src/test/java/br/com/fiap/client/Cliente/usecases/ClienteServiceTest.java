package br.com.fiap.client.Cliente.usecases;


import br.com.fiap.client.Cliente.application.usecases.ClienteService;
import br.com.fiap.client.Cliente.domain.entities.Cliente;
import br.com.fiap.client.Cliente.interfaces.gateways.ClienteGateway;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private final ClienteGateway gateway = mock(ClienteGateway.class);
    private final ClienteService service = new ClienteService(gateway);

    @Test
    void deveSalvarClienteComSucesso() {
        Cliente cliente = new Cliente(null, "Vinicius", "12345678901", LocalDate.now(), List.of());

        when(gateway.salvar(any())).thenReturn(cliente);

        Cliente salvo = service.salvar(cliente);

        assertNotNull(salvo);
        assertEquals("Vinicius", salvo.getNome());
        verify(gateway).salvar(cliente);
    }

    @Test
    void deveBuscarClientePorId() {
        UUID id = UUID.randomUUID();
        Cliente cliente = new Cliente(id, "Maria", "98765432100", LocalDate.now(), List.of());

        when(gateway.buscarPorId(id)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = service.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("Maria", resultado.get().getNome());
    }
}