package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.repository.pedido.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPedidoService {

    @Autowired
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void finalizar(Integer id) {
        Pedido pedido = buscarPedidoPorIdService.find(id);
        pedido.setAtivo(false);
        pedido.getItens().forEach(item -> item.setStatus(Status.I));
        pedidoRepository.save(pedido);
    }
}
