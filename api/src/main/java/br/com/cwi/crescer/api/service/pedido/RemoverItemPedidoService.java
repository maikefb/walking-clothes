package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.repository.pedido.PedidoRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverItemPedidoService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CancelarPedidoService cancelarPedidoService;

    public void remover(Integer idItem, Integer idPedido) {
        Pedido pedido = buscarPedidoPorIdService.find(idPedido);
        Item item = buscarItemPorIdService.find(idItem);
        pedido.getItens().remove(item);
        if (pedido.getItens().isEmpty()) {
            cancelarPedidoService.cancelar(idPedido);
            return;
        }
        pedidoRepository.save(pedido);
    }
}
