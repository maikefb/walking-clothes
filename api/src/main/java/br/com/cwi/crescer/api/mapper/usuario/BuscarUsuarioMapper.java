package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarUsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BuscarUsuarioResponse apply(Usuario usuario) {
        return modelMapper.map(usuario, BuscarUsuarioResponse.class);
    }

}
