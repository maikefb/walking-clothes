package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.PerfilUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.PerfilUsuarioResponseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExibirPerfilUsuarioServiceTest {

    @InjectMocks
    private ExibirPerfilUsuarioService tested;

    @Mock
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Mock
    private PerfilUsuarioResponseMapper perfilUsuarioResponseMapper;

    @Test
    public void deveExibirPerfilDoUsuario() {
        int idUsuario = 1;
        int pag = 1;
        Usuario usuario = new Usuario();
        PerfilUsuarioResponse response = new PerfilUsuarioResponse();

        Mockito.when(buscarUsuarioPorIdService.apply(idUsuario)).thenReturn(usuario);
        Mockito.when(perfilUsuarioResponseMapper.apply(usuario, pag)).thenReturn(response);
        tested.apply(idUsuario, pag);

        Mockito.verify(buscarUsuarioPorIdService).apply(idUsuario);
        Mockito.verify(perfilUsuarioResponseMapper).apply(usuario, pag);
    }

}
