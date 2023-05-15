package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelarPedidoService {

    @Autowired
    private BuscarPedidoPorIdService buscarPedidoPorIdService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    public void cancelar(Integer id) {
        Usuario usuario = usuarioLogadoService.get();
        Pedido pedido = buscarPedidoPorIdService.find(id);
        for (Item item : pedido.getItens()) {
            item.setStatus(Status.D);
        }
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem("Usuário cancelou o pedido");
        notificacao.setIdUsuario(pedido.getVendedor());
        pedido.getVendedor().getNotificacoes().add(notificacao);
        pedido.setAtivo(false);
        usuarioRepository.save(usuario);
    }
}
