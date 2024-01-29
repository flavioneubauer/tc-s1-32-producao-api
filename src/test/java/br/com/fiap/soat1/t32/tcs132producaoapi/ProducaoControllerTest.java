package br.com.fiap.soat1.t32.tcs132producaoapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.fiap.soat1.t32.clients.PedidoClient;
import br.com.fiap.soat1.t32.controllers.ProducaoController;
import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.services.PedidoService;

@WebMvcTest(ProducaoController.class)
@ExtendWith(MockitoExtension.class)
public class ProducaoControllerTest {

	@Autowired(required = true)
	private MockMvc mockMvc;

	@Mock
    private PedidoClient pedidoClient;

	@MockBean
    private PedidoService pedidoService;
	
	@BeforeEach
	void setUp() {
		pedidoService = new PedidoService(pedidoClient);
	}
	
	@Test
	void testarAlterarStatusPreparacaoPedidoComSucesso() throws Exception {
		mockMvc.perform(put("/v1/producao/1/" + StatusPreparacaoPedido.EM_PREPARACAO.name()).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is2xxSuccessful())
			.andReturn();
	}
	
	@Test
	void listarPedidosTesteStatus() throws Exception {
		mockMvc.perform(get("/v1/producao").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is2xxSuccessful())
			.andReturn();
	}
}
