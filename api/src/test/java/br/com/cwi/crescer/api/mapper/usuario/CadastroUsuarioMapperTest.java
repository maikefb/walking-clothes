package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.CadastroUsuarioRequest;
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
public class CadastroUsuarioMapperTest {

    @InjectMocks
    private CadastroUsuarioMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearOUsuario() {
        CadastroUsuarioRequest request = new CadastroUsuarioRequest();
        Usuario usuario = new Usuario();

        Mockito.when(modelMapper.map(request, Usuario.class)).thenReturn(usuario);
        Usuario result = tested.apply(request);

        Mockito.verify(modelMapper).map(request, Usuario.class);
        Assert.assertEquals(usuario.getNome(), result.getNome());
        Assert.assertEquals(usuario.getEmail(), result.getEmail());
        Assert.assertEquals(usuario.getPassword(), result.getPassword());
        Assert.assertEquals(usuario.getDataNascimento(), result.getDataNascimento());
    }
}
