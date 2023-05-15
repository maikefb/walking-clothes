package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioMapperTest {

    @InjectMocks
    private BuscarUsuarioMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearOBuscarUsuarioResponse() {
        Usuario usuario = new Usuario();
        BuscarUsuarioResponse buscarUsuarioResponse = new BuscarUsuarioResponse();

        Mockito.when(modelMapper.map(usuario, BuscarUsuarioResponse.class)).thenReturn(buscarUsuarioResponse);
        BuscarUsuarioResponse result = tested.apply(usuario);

        Mockito.verify(modelMapper).map(usuario, BuscarUsuarioResponse.class);
        Assert.assertEquals(usuario.getIdUsuario(), result.getIdUsuario());
        Assert.assertEquals(usuario.getNome(), result.getNome());
        Assert.assertEquals(usuario.getFotoPerfil(), result.getFotoPerfil());
    }
}
