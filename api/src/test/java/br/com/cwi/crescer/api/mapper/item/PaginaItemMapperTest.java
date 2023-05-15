package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.response.item.PaginaDoItemResponse;
import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.service.CalculaDistanciaService;
import br.com.cwi.crescer.api.service.imagem.ListarImagensItemService;
import br.com.cwi.crescer.api.service.item.VerificarItemFavoritoService;
import br.com.cwi.crescer.api.service.tagitem.ListarTagsDoItemService;
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
public class PaginaItemMapperTest {

    @InjectMocks
    private PaginaItemMapper tested;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CalculaDistanciaService calculaDistanciaService;

    @Mock
    private ListarImagensItemService listarImagensItemService;

    @Mock
    private ListarTagsDoItemService listarTagsDoItemService;

    @Mock
    private VerificarItemFavoritoService verificarItemFavoritoService;

    @Test
    public void deveMapearOPaginaDoItemResponse() {
        Usuario usuario = new Usuario();
        PaginaDoItemResponse paginaDoItemResponse = new PaginaDoItemResponse();
        Endereco endereco1 = new Endereco();
        endereco1.setLatitude(BigDecimal.ZERO);
        endereco1.setLongitude(BigDecimal.ZERO);
        usuario.setEndereco(endereco1);
        usuario.setIdUsuario(1);
        Item item = new Item();
        item.setCor(Cor.AZUL);
        item.setEstadoUso(EstadoUso.U);
        item.setStatus(Status.N);
        item.setTipo(Tipo.BLAZER);
        Armario armario = new Armario();
        item.setIdArmario(armario);
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1);
        armario.setUsuario(usuario1);
        Endereco endereco = new Endereco();
        endereco.setLatitude(BigDecimal.ZERO);
        endereco.setLongitude(BigDecimal.ZERO);
        Municipio municipio = new Municipio();
        municipio.setNome("");
        municipio.setEstado(new Estado());
        endereco.setCidade(municipio);
        usuario1.setEndereco(endereco);
        double distancia = 0.0;

        List<String> imagensItem = new ArrayList<>();
        List<String> tags = new ArrayList<>();

        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(modelMapper.map(item, PaginaDoItemResponse.class)).thenReturn(paginaDoItemResponse);
        Mockito.when(calculaDistanciaService.apply(usuario.getEndereco().getLatitude().doubleValue(), usuario.getEndereco().getLongitude().doubleValue(),
                armario.getUsuario().getEndereco().getLatitude().doubleValue(), armario.getUsuario().getEndereco().getLongitude().doubleValue())).thenReturn(distancia);
        Mockito.when(listarImagensItemService.apply(item.getImagens())).thenReturn(imagensItem);
        Mockito.when(listarTagsDoItemService.apply(item.getTagsEstilos())).thenReturn(tags);
        Mockito.when(verificarItemFavoritoService.apply(item)).thenReturn(false);
        PaginaDoItemResponse result = tested.apply(item);

        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(modelMapper).map(item, PaginaDoItemResponse.class);
        Mockito.verify(calculaDistanciaService).apply(usuario.getEndereco().getLatitude().doubleValue(), usuario.getEndereco().getLongitude().doubleValue(),
                armario.getUsuario().getEndereco().getLatitude().doubleValue(), armario.getUsuario().getEndereco().getLongitude().doubleValue());
        Mockito.verify(listarImagensItemService).apply(item.getImagens());
        Mockito.verify(listarTagsDoItemService).apply(item.getTagsEstilos());
        Mockito.verify(verificarItemFavoritoService).apply(item);
        Assert.assertEquals(item.getIdItem(), result.getIdItem());
        Assert.assertEquals(item.getTitulo(), result.getTitulo());
        Assert.assertEquals(item.getPreco(), result.getPreco());
        Assert.assertEquals(item.getTamanho(), result.getTamanho());
    }
}
