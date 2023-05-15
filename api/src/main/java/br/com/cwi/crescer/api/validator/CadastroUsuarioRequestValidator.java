package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.controller.request.usuario.CadastroUsuarioRequest;
import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroUsuarioRequestValidator {

    @Autowired
    UsuarioRepository usuarioRepository;

    public void accept(CadastroUsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail()))
            throw new ValidacaoNegocioException("Email já está em uso.");
    }
}
