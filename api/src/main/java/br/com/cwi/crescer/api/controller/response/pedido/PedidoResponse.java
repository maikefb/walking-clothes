package br.com.cwi.crescer.api.controller.response.pedido;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PedidoResponse {
    private Integer idPedido;
    private List<ItemArmarioResponse> itens;
    private Integer idChat;
    private LocalDate data;
    private boolean isAtivo;
}
