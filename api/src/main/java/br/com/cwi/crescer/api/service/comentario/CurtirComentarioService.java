package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Curtida;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.curtida.CurtidaRepository;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurtirComentarioService {

    @Autowired
    private BuscarComentarioPorIdService buscarComentarioPorIdService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private CurtidaRepository repository;

    public void curtir(Integer idComentario) {
        Usuario usuario = usuarioLogadoService.get();
        Optional<Curtida> curtida = repository.findCurtidaByUsuario(usuario, idComentario);
        if (curtida.isPresent()) {
            repository.delete(curtida.get());
            return; }
        curtida = Optional.of(new Curtida());
        Comentario comentario = buscarComentarioPorIdService.find(idComentario);
        curtida.get().setComentario(comentario);
        curtida.get().setUsuario(usuario);
        repository.save(curtida.get());
    }

}


