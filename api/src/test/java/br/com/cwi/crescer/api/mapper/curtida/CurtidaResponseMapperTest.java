package br.com.cwi.crescer.api.mapper.curtida;

import br.com.cwi.crescer.api.controller.response.CurtidaResponse;
import br.com.cwi.crescer.api.domain.Curtida;
import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurtidaResponseMapperTest {

    @Test
    public void deveMapearOCurtidaResponse() {
        CurtidaResponseMapper tested = new CurtidaResponseMapper();
        Curtida curtida = new Curtida();
        Usuario usuario = new Usuario();
        curtida.setUsuario(usuario);

        CurtidaResponse result = tested.convert(curtida);

        Assert.assertEquals(curtida.getUsuario().getIdUsuario(), result.getIdUsuario());
    }
}
