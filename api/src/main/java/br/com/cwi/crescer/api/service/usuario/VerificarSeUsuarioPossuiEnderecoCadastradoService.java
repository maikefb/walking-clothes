package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.PossuiEnderecoResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificarSeUsuarioPossuiEnderecoCadastradoService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    public PossuiEnderecoResponse apply() {
        PossuiEnderecoResponse response = new PossuiEnderecoResponse();
        Usuario usuario = usuarioLogadoService.get();
        response.setPossuiEnderecoCadastrado(usuario.getEndereco() != null);
        return response;
    }

}
