package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.EdicaoPerfilRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.EdicaoPerfilMapper;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarPerfilService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private EdicaoPerfilMapper edicaoPerfilMapper;

    @Autowired
    private UsuarioRepository repository;

    public void editar(EdicaoPerfilRequest request) {
        Usuario usuario = usuarioLogadoService.get();
        edicaoPerfilMapper.apply(usuario, request);
        repository.save(usuario);
    }

}
