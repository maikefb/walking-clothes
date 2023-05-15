package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.armario.BuscarArmarioResponseMapper;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarArmariosUsuarioServiceTest {

    @InjectMocks
    private BuscarArmariosUsuarioService tested;

    @Mock
    private ArmarioRepository repository;

    @Mock
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    @Mock
    private BuscarArmarioResponseMapper buscarArmarioResponseMapper;

    @Test
    public void deveRetornarOTamanhoDaListaBuscaArmarioResponse() {
        int id = 0;
        int pagePost = 1;
        int armarioPorPagina = 5;
        String nome = "nome";
        String nomeArmario = "TituloArmario";
        String tag = "tag1";
        String titulo = "Titulo1";
        String tamanho = "GG";
        Pageable pageable = PageRequest.of(pagePost, armarioPorPagina);

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);


        ItemArmarioResponse itemResponse = new ItemArmarioResponse(id,titulo,tamanho, BigDecimal.valueOf(20.06),new ArrayList<>());
        Item item = new Item();
        item.setIdItem(id);
        item.setTamanho(tamanho);
        item.setPreco(BigDecimal.valueOf(20.06));
        item.setImagens(new ArrayList<>());
        item.setTitulo(titulo);


        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        List<ItemArmarioResponse> itens = new ArrayList<>();
        itens.add(itemResponse);

        Armario armario = new Armario(id,nomeArmario,new TagEstilo(id,tag),itemList,usuario);

        List<Armario> armariosList = new ArrayList<>();
        armariosList.add(armario);
        Page<Armario> armarioPage = new PageImpl<>(armariosList,pageable,1);

        BuscarArmarioResponse armarioResponse = new BuscarArmarioResponse(id,nomeArmario,tag,itens,nome,null,0.0,1);

        Mockito.when(repository.findByUsuarioIdUsuario(id,pageable)).thenReturn(armarioPage);
        Mockito.when(itemArmarioResponseMapper.apply(item)).thenReturn(itemResponse);
        Mockito.when(buscarArmarioResponseMapper.apply(armario,itens)).thenReturn(armarioResponse);

        List<BuscarArmarioResponse> result = tested.buscar(id, pagePost);


        Mockito.verify(repository).findByUsuarioIdUsuario(id,pageable);
        Mockito.verify(itemArmarioResponseMapper).apply(item);
        Mockito.verify(buscarArmarioResponseMapper).apply(armario,itens);

        Assert.assertEquals(armariosList.size(),result.size());
    }

}
