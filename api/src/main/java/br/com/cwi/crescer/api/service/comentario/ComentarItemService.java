package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.request.comentario.ComentarRequest;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.comentario.ComentarioRepository;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarItemService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void comentar(ComentarRequest comentarRequest, Integer id) {
        Item item = buscarItemPorIdService.find(id);
        Usuario usuario = usuarioLogadoService.get();
        Comentario comentario = new Comentario();

        comentario.setMensagem(comentarRequest.getMensagem());
        comentario.setIdUsuario(usuario);
        comentario.setIdItem(item);
        item.getComentarios().add(comentario);

        Notificacao notificacao = new Notificacao();
        notificacao.setIsativa(true);
        notificacao.setIdUsuario(item.getIdArmario().getUsuario());
        notificacao.setMensagem(String.format("%s comentou no item %s: %s", usuario.getNome(), item.getTitulo(), comentario.getMensagem()));
        Usuario vendedor = item.getIdArmario().getUsuario();
        vendedor.getNotificacoes().add(notificacao);

        comentarioRepository.save(comentario);
        usuarioRepository.save(vendedor);
    }
}
