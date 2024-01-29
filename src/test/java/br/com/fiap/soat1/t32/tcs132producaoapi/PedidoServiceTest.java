package br.com.fiap.soat1.t32.tcs132producaoapi;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.fiap.soat1.t32.clients.PedidoClient;
import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import br.com.fiap.soat1.t32.services.PedidoService;
import feign.FeignException;

@SpringBootTest
class PedidoServiceTest {
	
	@Mock
    private PedidoClient pedidoClient;

	@MockBean
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoService(pedidoClient);
    }

	@Test
	void testeAlterarStatusPedido() throws Exception {
		pedidoService.alterarStatusPreparacaoPedido(10L, StatusPreparacaoPedido.RECEBIDO);

		verify(pedidoClient).alterarStatusPreparacaoPedido(anyLong(), any(StatusPreparacaoPedido.class));
	}
	
	@Test
	void deveAlterarStatusPreparacaoPedido_FeignException() {
		when(pedidoClient.alterarStatusPreparacaoPedido(anyLong(), any(StatusPreparacaoPedido.class))).thenThrow(FeignException.class);

		assertThrows(IntegrationException.class, () -> pedidoService.alterarStatusPreparacaoPedido(10L, StatusPreparacaoPedido.RECEBIDO));
	}
	
	@Test
	void listarPedidos() throws Exception {
		pedidoService.listar();

		verify(pedidoClient).listarPedidos();
	}
	
	@Test
	void deveListar_FeignException() {
		when(pedidoClient.listarPedidos()).thenThrow(FeignException.class);

		assertThrows(IntegrationException.class, () -> pedidoService.listar());
	}
}