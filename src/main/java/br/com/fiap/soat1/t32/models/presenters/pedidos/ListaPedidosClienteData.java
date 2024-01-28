package br.com.fiap.soat1.t32.models.presenters.pedidos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPedidosClienteData {

    private UUID id;
    private String nome;

}
