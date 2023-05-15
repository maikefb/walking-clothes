package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.pedido.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarPedidoPorIdService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findByIdPedido(id);
        if (!pedido.isPresent()) {
            throw new RegistroNaoEncontradoException("Pedido");
        }

        return pedido.get();
    }
}
