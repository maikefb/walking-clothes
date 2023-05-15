package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.usuario.PerfilUsuarioResponse;
import br.com.cwi.crescer.api.domain.Endereco;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.service.armario.BuscarArmariosUsuarioService;
import br.com.cwi.crescer.api.service.usuario.ContarItensAVendaUsuarioService;
import br.com.cwi.crescer.api.service.usuario.ContarItensVendidosPeloUsuarioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PerfilUsuarioResponseMapperTest {

    @InjectMocks
    private PerfilUsuarioResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ContarItensVendidosPeloUsuarioService contarItensVendidosPeloUsuarioService;

    @Mock
    private ContarItensAVendaUsuarioService contarItensAVendaUsuarioService;

    @Mock
    private BuscarArmariosUsuarioService buscarArmariosUsuarioService;

    @Test
    public void deveMapearOPerfilUsuarioResponse() {
        Usuario usuario = new Usuario();
        usuario.setEndereco(new Endereco());
        int pagePost = 0;
        PerfilUsuarioResponse response = new PerfilUsuarioResponse();
        int itensVendidos = 0;
        int itensAVenda = 0;
        List<BuscarArmarioResponse> armarios = new ArrayList<>();

        Mockito.when(modelMapper.map(usuario, PerfilUsuarioResponse.class)).thenReturn(response);
        Mockito.when(contarItensVendidosPeloUsuarioService.apply(usuario.getIdUsuario())).thenReturn(itensVendidos);
        Mockito.when(contarItensAVendaUsuarioService.apply(usuario.getIdUsuario())).thenReturn(itensAVenda);
        Mockito.when(buscarArmariosUsuarioService.buscar(usuario.getIdUsuario(), pagePost)).thenReturn(armarios);
        PerfilUsuarioResponse result = tested.apply(usuario, pagePost);

        Mockito.verify(modelMapper).map(usuario, PerfilUsuarioResponse.class);
        Mockito.verify(contarItensVendidosPeloUsuarioService).apply(usuario.getIdUsuario());
        Mockito.verify(contarItensAVendaUsuarioService).apply(usuario.getIdUsuario());
        Mockito.verify(buscarArmariosUsuarioService).buscar(usuario.getIdUsuario(), pagePost);
        Assert.assertEquals(usuario.getNome(), result.getNome());
        Assert.assertEquals(usuario.getFotoPerfil(), result.getFotoPerfil());
        Assert.assertEquals(usuario.getEndereco().getCidade(), result.getCidade());
        Assert.assertEquals(itensVendidos, (int) result.getQtdItensVendidos());
        Assert.assertEquals(itensAVenda, (int) result.getQtdItensAVenda());
    }
}
