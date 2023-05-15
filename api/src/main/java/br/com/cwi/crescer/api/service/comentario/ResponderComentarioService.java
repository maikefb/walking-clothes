package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.request.comentario.ComentarRequest;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Notificacao;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.comentario.ComentarioRepository;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponderComentarioService {

    @Autowired
    private BuscarComentarioPorIdService comentarioPorIdService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void responder(ComentarRequest comentarRequest, Integer id) {
        Usuario usuario = usuarioLogadoService.get();
        Comentario comentario = comentarioPorIdService.find(id);
        Comentario resposta = new Comentario();

        resposta.setIdUsuario(usuario);
        resposta.setMensagem(comentarRequest.getMensagem());
        resposta.setIdItem(comentario.getIdItem());
        comentario.getComentarios().add(resposta);

        Notificacao notificacao = new Notificacao();
        notificacao.setIsativa(true);
        notificacao.setIdUsuario(comentario.getIdUsuario());
        notificacao.setMensagem(String.format("%s respondeu seu comentário: %s", usuario.getNome(), comentarRequest.getMensagem()));
        Usuario autorComentarioBase = comentario.getIdUsuario();
        autorComentarioBase.getNotificacoes().add(notificacao);

        comentarioRepository.save(comentario);
        usuarioRepository.save(autorComentarioBase);
    }
}
