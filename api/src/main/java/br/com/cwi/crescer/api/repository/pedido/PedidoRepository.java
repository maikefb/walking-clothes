package br.com.cwi.crescer.api.repository.pedido;

import br.com.cwi.crescer.api.domain.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PedidoRepository extends CrudRepository<Pedido, Integer> {
    Optional<Pedido> findByIdPedido(Integer id);
}
