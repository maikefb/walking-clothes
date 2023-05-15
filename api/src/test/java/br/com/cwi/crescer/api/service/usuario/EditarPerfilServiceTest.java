package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.EdicaoPerfilRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.EdicaoPerfilMapper;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EditarPerfilServiceTest {

    @InjectMocks
    private EditarPerfilService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private EdicaoPerfilMapper edicaoPerfilMapper;

    @Mock
    private UsuarioRepository repository;

    @Test
    public void deveEditarPerfilDoUsuario() {
        EdicaoPerfilRequest request = new EdicaoPerfilRequest();
        Usuario usuario = new Usuario();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(edicaoPerfilMapper.apply(usuario, request)).thenReturn(usuario);
        tested.editar(request);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(edicaoPerfilMapper).apply(usuario, request);
        Mockito.verify(repository).save(usuario);
    }

}
