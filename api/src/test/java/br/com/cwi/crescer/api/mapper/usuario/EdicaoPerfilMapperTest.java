package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.EdicaoPerfilRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EdicaoPerfilMapperTest {

    @Test
    public void deveMapearOUsuarioEditado() {
        EdicaoPerfilMapper tested = new EdicaoPerfilMapper();

        Usuario usuario = new Usuario();
        EdicaoPerfilRequest request = new EdicaoPerfilRequest();

        Usuario result = tested.apply(usuario, request);

        Assert.assertEquals(request.getNome(), result.getNome());
        Assert.assertEquals(request.getDescricao(), result.getDescricao());
        Assert.assertEquals(request.getEmail(), result.getEmail());
        Assert.assertEquals(request.getFotoPerfil(), result.getFotoPerfil());
    }
}
