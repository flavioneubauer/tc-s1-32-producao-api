package br.com.fiap.soat1.t32.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.models.presenters.pedidos.ListaPedidosResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	@Value("${api.pedidos.url.alterar.status}")
	private String urlAlterarStatus;
	
	@Value("${api.pedidos.url.listar.pedidos}")
	private String urlListarPedidos;
	
	public void atualizarstatusPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido) {
		RestTemplate restTemplate = new RestTemplate();
		
		StringBuilder url = new StringBuilder();
		url.append(urlAlterarStatus).append("/");
		url.append(id).append("/");
		url.append(statusPreparacaoPedido);
		
		restTemplate.put(url.toString(), restTemplate);
	}
	
	public ResponseEntity<ListaPedidosResponse> listar() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForEntity(urlListarPedidos,ListaPedidosResponse.class);
	}
}