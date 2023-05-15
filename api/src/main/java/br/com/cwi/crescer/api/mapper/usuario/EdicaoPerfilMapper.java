package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.EdicaoPerfilRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EdicaoPerfilMapper {

    public Usuario apply(Usuario usuario, EdicaoPerfilRequest request) {
        usuario.setNome(request.getNome());
        usuario.setDescricao(request.getDescricao());
        usuario.setEmail(request.getEmail());
        usuario.setFotoPerfil(request.getFotoPerfil());
        return usuario;
    }
}
