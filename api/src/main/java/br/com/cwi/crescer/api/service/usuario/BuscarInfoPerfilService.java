package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.mapper.usuario.BuscarUsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarInfoPerfilService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private BuscarUsuarioMapper buscarUsuarioMapper;

    public BuscarUsuarioResponse find() {
        return buscarUsuarioMapper.apply(usuarioLogadoService.get());
    }
}
