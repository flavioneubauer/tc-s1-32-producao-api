package br.com.fiap.soat1.t32.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.clients.PedidoClient;
import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.exceptions.IntegrationException;
import br.com.fiap.soat1.t32.models.presenters.pedidos.ListaPedidosResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	private final PedidoClient pedidoClient;
	
	public void alterarStatusPreparacaoPedido(Long id, StatusPreparacaoPedido statusPreparacaoPedido) {
		try {
            pedidoClient.alterarStatusPreparacaoPedido(id, statusPreparacaoPedido);
        } catch(FeignException fe) {
            throw new IntegrationException("Houve um erro na alteração de status de preparação do pedido.");
        }
	}
	
	public ResponseEntity<ListaPedidosResponse> listar() {
		try {
            return pedidoClient.listarPedidos();
        } catch(FeignException fe) {
            throw new IntegrationException("Houve um erro na busca de pedidos.");
        }
	}
}