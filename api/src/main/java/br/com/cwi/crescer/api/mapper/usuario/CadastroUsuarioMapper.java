package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.CadastroUsuarioRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CadastroUsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario apply(CadastroUsuarioRequest request) {
        return modelMapper.map(request, Usuario.class);
    }
}
