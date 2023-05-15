package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Item;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarArmarioPorNomeServiceTest {

    @InjectMocks
    private BuscarArmarioPorNomeService tested;

    @Mock
    private ArmarioRepository armarioRepository;

    @Mock
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    @Mock
    private BuscarArmarioResponseMapper buscarArmarioResponseMapper;

    @Test
    public void teste() {
        String nome = "nome";
        int pagePost = 1;
        int armarioPorPagina = 5;

        Pageable pageable = PageRequest.of(pagePost, armarioPorPagina);
        ItemArmarioResponse response = new ItemArmarioResponse();
        List<ItemArmarioResponse> itemResponse = new ArrayList<>();
        BuscarArmarioResponse armarioResponse = new BuscarArmarioResponse();
        List<Armario> armarios = Arrays.asList(new Armario());
        List<Item> itens = Arrays.asList(new Item());

        itemResponse.add(response);
        armarioResponse.setNome(nome);
        armarios.get(0).setItens(itens);

        Page<Armario> pageArmario = new PageImpl<>(armarios, pageable, armarios.size());

        Mockito.when(armarioRepository.findByNome(nome, pageable)).thenReturn(pageArmario);
        Mockito.when(itemArmarioResponseMapper.apply(itens.get(0))).thenReturn(response);
        Mockito.when(buscarArmarioResponseMapper.apply(armarios.get(0), itemResponse)).thenReturn(armarioResponse);



        List<BuscarArmarioResponse> result = tested.buscar(nome, pagePost);

        Mockito.verify(armarioRepository).findByNome(nome, pageable);
        Mockito.verify(itemArmarioResponseMapper).apply(itens.get(0));
        Mockito.verify(buscarArmarioResponseMapper).apply(armarios.get(0),itemResponse);

        Assert.assertEquals(nome, result.get(0).getNome());
    }

}
