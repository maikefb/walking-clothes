package br.com.cwi.crescer.api.mapper.pedido;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.controller.response.pedido.PedidoResponse;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResponseMapper {
    @Autowired
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    public PedidoResponse apply(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.setIdChat(pedido.getChat().getIdChat());
        response.setIdPedido(pedido.getIdPedido());
        response.setData(pedido.getData());
        response.setAtivo(pedido.isAtivo());
        List<ItemArmarioResponse> itens = pedido.getItens().stream().map(item ->
                itemArmarioResponseMapper.apply(item)).
                collect(Collectors.toList());
        response.setItens(itens);
        return response;
    }
}
