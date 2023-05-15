package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarItemPorIdServiceTest {

    @InjectMocks
    private BuscarItemPorIdService tested;

    @Mock
    private ItemRepository repository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoNaoEncontrarOItemComIdInformado() {
        int id = 1;

        tested.find(id);

        Mockito.verify(repository).findByIdItem(id);
    }

    @Test
    public void deveRetornarOItemQuandoForEncontrado() {
        int id = 1;
        Item item = new Item();

        Mockito.when(repository.findByIdItem(id)).thenReturn(Optional.of(item));
        Item result = tested.find(id);

        Mockito.verify(repository).findByIdItem(id);
        Assert.assertNotNull(result);
    }
}
