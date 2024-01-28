package br.com.fiap.soat1.t32.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.soat1.t32.enums.StatusPreparacaoPedido;
import br.com.fiap.soat1.t32.models.presenters.RespostaErro;
import br.com.fiap.soat1.t32.models.presenters.pedidos.ListaPedidosResponse;
import br.com.fiap.soat1.t32.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Producao", description = "API de Producao")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class ProducaoController {
	
	private final PedidoService pedidoService;

	@PutMapping("/v1/pedidos/{id}/{status}")
	@ApiResponse(responseCode = "204", description = "Status de preparação do pedido alterado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Altera status de preparação do pedido")
	public ResponseEntity<Void> atualizarstatusPedido(@PathVariable Long id, @PathVariable StatusPreparacaoPedido status) {
		pedidoService.atualizarstatusPedido(id, status);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/v1/pedidos")
	@ApiResponse(responseCode = "200", description = "Status de pedido retornado com sucesso")
	@ApiResponse(responseCode = "422", description = "Erro de validação",
			content = @Content(schema = @Schema(implementation = RespostaErro.class)))
	@Operation(description = "Lista pedidos")
	public ResponseEntity<ListaPedidosResponse> listarPedidos() {
		return pedidoService.listar();
	}
}
