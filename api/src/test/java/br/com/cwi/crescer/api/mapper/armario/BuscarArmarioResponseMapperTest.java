package br.com.cwi.crescer.api.mapper.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Endereco;
import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.service.CalculaDistanciaService;
import br.com.cwi.crescer.api.service.armario.ContarItensAVendaArmarioService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarArmarioResponseMapperTest {

    @InjectMocks
    private BuscarArmarioResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ContarItensAVendaArmarioService contarItensAVendaArmarioService;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private CalculaDistanciaService calculaDistanciaService;

    @Test
    public void deveMapearOArmarioResponse() {
        BuscarArmarioResponse buscarArmarioResponse = new BuscarArmarioResponse();
        Armario armario = new Armario();
        TagEstilo tag = new TagEstilo();
        tag.setNome("nome");
        armario.setTagDeEstilo(tag);
        Endereco endereco1 = new Endereco();
        endereco1.setLatitude(BigDecimal.ZERO);
        endereco1.setLongitude(BigDecimal.ZERO);
        Usuario usuario = new Usuario();
        armario.setUsuario(usuario);
        usuario.setEndereco(endereco1);
        List<ItemArmarioResponse> itens = new ArrayList<>();
        Usuario comprador = new Usuario();
        Endereco endereco = new Endereco();
        endereco.setLatitude(BigDecimal.ZERO);
        endereco.setLongitude(BigDecimal.ZERO);
        comprador.setEndereco(endereco);
        int qtdItens = 0;
        double distancia = 0.0;

        Mockito.when(modelMapper.map(armario, BuscarArmarioResponse.class)).thenReturn(buscarArmarioResponse);
        Mockito.when(usuarioLogadoService.get()).thenReturn(comprador);
        Mockito.when(calculaDistanciaService.apply(comprador.getEndereco().getLatitude().doubleValue(), comprador.getEndereco().getLongitude().doubleValue(),
                armario.getUsuario().getEndereco().getLatitude().doubleValue(), armario.getUsuario().getEndereco().getLongitude().doubleValue())).thenReturn(distancia);
        Mockito.when(contarItensAVendaArmarioService.apply(armario.getIdArmario())).thenReturn(qtdItens);
        BuscarArmarioResponse result = tested.apply(armario, itens);

        Mockito.verify(modelMapper).map(armario, BuscarArmarioResponse.class);
        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(calculaDistanciaService).apply(comprador.getEndereco().getLatitude().doubleValue(), comprador.getEndereco().getLongitude().doubleValue(),
                armario.getUsuario().getEndereco().getLatitude().doubleValue(), armario.getUsuario().getEndereco().getLongitude().doubleValue());
        Mockito.verify(contarItensAVendaArmarioService).apply(armario.getIdArmario());

        Assert.assertEquals(armario.getTagDeEstilo().getNome(), result.getTagDeEstilo());
        Assert.assertSame(armario.getNome(), result.getNome());
    }
}
