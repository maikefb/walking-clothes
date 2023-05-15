package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioPorNomeServiceTest {

    @InjectMocks
    private BuscarUsuarioPorNomeService tested;

    @Mock
    private UsuarioRepository repository;

    @Test
    public void deveRetornarListaVaziaQuandoNenhumRegistroForEncontrado() {
        String nome = "Nome";
        int tamanhoListaEsperado = 0;

        List<BuscarUsuarioResponse> usuarios = tested.buscar(nome);

        Mockito.verify(repository).findByNome(nome);
        Assert.assertEquals(tamanhoListaEsperado, usuarios.size());
    }

    @Test
    public void deveRetornarListaDeUsuarios() {
        String nome = "Nome";
        List<Usuario> usuarios = new ArrayList<>();

        Mockito.when(repository.findByNome(nome)).thenReturn(usuarios);
        tested.buscar(nome);

        Mockito.verify(repository).findByNome(nome);
        Assert.assertNotNull(usuarios);
    }

}
