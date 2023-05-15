package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUsuarioPorIdService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario apply(Integer idUsuario) {
        Optional<Usuario> usuario = repository.findByIdUsuario(idUsuario);

        if (!usuario.isPresent()) {
            throw new RegistroNaoEncontradoException("Usuário");
        }

        return usuario.get();
    }

}
