package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.request.armario.CadastrarArmarioRequest;
import br.com.cwi.crescer.api.controller.response.armario.CadastrarArmarioResponse;
import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarArmarioServiceTest {

    @InjectMocks
    private CadastrarArmarioService tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Mock
    private ArmarioRepository armarioRepository;

    @Test
    public void deveEncontrarATagECadastrarOArmario() {
        CadastrarArmarioRequest request = new CadastrarArmarioRequest();
        Usuario usuario = new Usuario();
        TagEstilo tagEstilo = new TagEstilo();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(buscarTagEstiloPorNomeService.apply(request.getTagEstilo())).thenReturn(tagEstilo);
        CadastrarArmarioResponse result = tested.cadastrar(request);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(buscarTagEstiloPorNomeService).apply(request.getTagEstilo());
        Assert.assertNotNull(result);
    }

    @Test
    public void deveCadastrarOArmarioCorretamente() {
        CadastrarArmarioRequest request = new CadastrarArmarioRequest();
        Usuario usuario = new Usuario();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(buscarTagEstiloPorNomeService.apply(request.getTagEstilo())).thenThrow(RegistroNaoEncontradoException.class);
        CadastrarArmarioResponse result = tested.cadastrar(request);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(buscarTagEstiloPorNomeService).apply(request.getTagEstilo());
        Assert.assertNotNull(result);
    }

}
