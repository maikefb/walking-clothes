package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.controller.response.pedido.PedidoResponse;
import br.com.cwi.crescer.api.mapper.pedido.PedidoResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPedidoResponsePorIdService {

    @Autowired
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Autowired
    private PedidoResponseMapper pedidoResponseMapper;

    public PedidoResponse find(Integer id) {
        return pedidoResponseMapper.apply(buscarPedidoPorIdService.find(id));
    }
}
