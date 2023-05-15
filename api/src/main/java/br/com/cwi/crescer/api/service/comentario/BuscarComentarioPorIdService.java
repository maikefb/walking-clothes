package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.comentario.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarComentarioPorIdService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario find(Integer id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);

        if (!comentario.isPresent()) {
            throw new RegistroNaoEncontradoException("Comentário");
        }
        return comentario.get();
    }
}
