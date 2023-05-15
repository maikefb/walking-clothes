package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.PerfilUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.PerfilUsuarioResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExibirPerfilUsuarioService {

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    private PerfilUsuarioResponseMapper perfilUsuarioResponseMapper;

    public PerfilUsuarioResponse apply(Integer idUsuario, Integer pagePost) {
        Usuario usuario = buscarUsuarioPorIdService.apply(idUsuario);
        return perfilUsuarioResponseMapper.apply(usuario, pagePost);
    }

}
