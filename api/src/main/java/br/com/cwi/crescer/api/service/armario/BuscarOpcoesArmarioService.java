package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.response.armario.OpcoesArmarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.armario.OpcoesArmarioResponseMapper;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarOpcoesArmarioService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private ArmarioRepository repository;

    @Autowired
    private OpcoesArmarioResponseMapper opcoesArmarioResponseMapper;

    public List<OpcoesArmarioResponse> buscar() {
        Usuario usuario = usuarioLogadoService.get();
        return repository.findByUsuarioIdUsuario(usuario.getIdUsuario())
                .stream()
                .map(armario -> opcoesArmarioResponseMapper.apply(armario))
                .collect(Collectors.toList());
    }
}
