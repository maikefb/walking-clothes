package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.mapper.item.BuscarItemMapper;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
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
public class BuscarItensPorIdArmarioServiceTest {

    @InjectMocks
    private BuscarItensPorIdArmarioService tested;

    @Mock
    private ItemRepository repository;

    @Mock
    private BuscarItemMapper buscarItemMapper;

    @Test
    public void deveBuscarListaDeItensDoArmarioDoIdInformadoEMontarResponse() {
        int idArmario = 0;
        List<Item> itens = new ArrayList<>();
        Item item = new Item();
        itens.add(item);
        BuscaItemResponse response = new BuscaItemResponse();
        int listSizeEsperado = 1;

        Mockito.when(repository.findByIdArmarioIdArmarioAndStatus(idArmario, Status.D)).thenReturn(itens);
        Mockito.when(buscarItemMapper.apply(item)).thenReturn(response);
        List<BuscaItemResponse> result = tested.buscar(idArmario);

        Mockito.verify(repository).findByIdArmarioIdArmarioAndStatus(idArmario, Status.D);
        Mockito.verify(buscarItemMapper).apply(item);
        Assert.assertEquals(listSizeEsperado, result.size());
    }
}
