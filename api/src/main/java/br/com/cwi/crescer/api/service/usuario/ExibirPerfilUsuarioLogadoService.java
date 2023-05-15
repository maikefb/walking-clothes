package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.PerfilUsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExibirPerfilUsuarioLogadoService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private ExibirPerfilUsuarioService exibirPerfilUsuarioService;

    public PerfilUsuarioResponse apply(Integer pagina) {
        return exibirPerfilUsuarioService.apply(usuarioLogadoService.get().getIdUsuario(), pagina);
    }
}
