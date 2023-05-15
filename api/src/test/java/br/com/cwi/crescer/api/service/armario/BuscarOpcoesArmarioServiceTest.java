package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.response.armario.OpcoesArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.armario.OpcoesArmarioResponseMapper;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarOpcoesArmarioServiceTest {

    @InjectMocks
    private BuscarOpcoesArmarioService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private ArmarioRepository repository;

    @Mock
    private OpcoesArmarioResponseMapper opcoesArmarioResponseMapper;

    @Test
    public void deveBuscarOpcoesDeArmariosDoUsuario() {
        Usuario usuario = new Usuario();
        Armario armario = new Armario();
        List<Armario> armarios = new ArrayList<>();
        armarios.add(armario);
        OpcoesArmarioResponse opcoesArmarioResponse = new OpcoesArmarioResponse();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(repository.findByUsuarioIdUsuario(usuario.getIdUsuario())).thenReturn(armarios);
        Mockito.when(opcoesArmarioResponseMapper.apply(armario)).thenReturn(opcoesArmarioResponse);
        tested.buscar();

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(repository).findByUsuarioIdUsuario(usuario.getIdUsuario());
        Mockito.verify(opcoesArmarioResponseMapper).apply(armario);
    }

}
