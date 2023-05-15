package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.controller.response.pedido.PedidoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.pedido.PedidoResponseMapper;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarPedidoService {
    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private PedidoResponseMapper pedidoResponseMapper;

    public List<PedidoResponse> get(){
        Usuario usuario = usuarioLogadoService.get();
        return usuario.getPedidos().stream().map(pedido ->
                pedidoResponseMapper.apply(pedido)).filter(PedidoResponse::isAtivo).collect(Collectors.toList());
    }
}
