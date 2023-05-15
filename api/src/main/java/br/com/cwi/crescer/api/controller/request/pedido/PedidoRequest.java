package br.com.cwi.crescer.api.controller.request.pedido;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PedidoRequest {
    Integer idVendedor;
    List<Integer> ids;
}
