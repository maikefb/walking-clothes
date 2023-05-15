package br.com.cwi.crescer.api.service.endereco;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.endereco.EnderecoRepository;
import br.com.cwi.crescer.api.service.usuario.BuscarUsuarioPorEmailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PossuiEnderecoServiceTest {

    @InjectMocks
    private PossuiEnderecoService tested;

    @Mock
    private EnderecoRepository repository;

    @Mock
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Test
    public void deveRetornarTrueQuandoUsuarioPossuirEndereco() {
        String email = "";
        Usuario usuario = new Usuario();

        Mockito.when(buscarUsuarioPorEmailService.buscar(email)).thenReturn(usuario);
        Mockito.when(repository.existsByIdEndereco(usuario.getIdUsuario())).thenReturn(true);
        boolean result = tested.apply(email);

        Mockito.verify(buscarUsuarioPorEmailService).buscar(email);
        Mockito.verify(repository).existsByIdEndereco(usuario.getIdUsuario());
        Assert.assertTrue(result);
    }

    @Test
    public void deveRetornarFalseQuandoUsuarioPossuirEndereco() {
        String email = "";
        Usuario usuario = new Usuario();

        Mockito.when(buscarUsuarioPorEmailService.buscar(email)).thenReturn(usuario);
        Mockito.when(repository.existsByIdEndereco(usuario.getIdUsuario())).thenReturn(false);
        boolean result = tested.apply(email);

        Mockito.verify(buscarUsuarioPorEmailService).buscar(email);
        Mockito.verify(repository).existsByIdEndereco(usuario.getIdUsuario());
        Assert.assertFalse(result);
    }
}