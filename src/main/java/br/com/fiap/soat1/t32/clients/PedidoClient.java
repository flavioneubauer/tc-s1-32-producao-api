package br.com.fiap.soat1.t32.clients;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.models.presenters.pedidos.ListaPedidosResponse;

@FeignClient(value = "${pedido.api.base-path}")
public interface PedidoClient {

    @PutMapping(value = "/v1/pedidos/{id}/{status}", consumes = APPLICATION_JSON_VALUE)
    Void alterarStatusPreparacaoPedido(@PathVariable Long id, @PathVariable StatusPreparacaoPedido status);

    @GetMapping(value="/v1/pedidos", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<ListaPedidosResponse> listarPedidos();
}