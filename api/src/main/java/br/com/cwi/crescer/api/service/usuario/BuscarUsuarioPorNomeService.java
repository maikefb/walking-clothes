package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.mapper.usuario.BuscarUsuarioMapper;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarUsuarioPorNomeService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BuscarUsuarioMapper buscarUsuarioMapper;

    public List<BuscarUsuarioResponse> buscar(String nome) {
        return repository.findByNome(nome)
                .stream()
                .map(u -> buscarUsuarioMapper.apply(u))
                .collect(Collectors.toList());
    }

}
