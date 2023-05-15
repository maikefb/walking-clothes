package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario get() {
        UserPrincipal userPrincipal;
        try {
            userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException e) {
            throw new ValidacaoNegocioException("Não foi possivel encontrar o usuario logado");
        }

        return usuarioRepository.findByEmail(userPrincipal.getEmail()).orElseThrow(() -> new RegistroNaoEncontradoException("Usuário"));
    }
}
