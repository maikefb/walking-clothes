package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContarItensAVendaArmarioServiceTest {

    @InjectMocks
    private ContarItensAVendaArmarioService tested;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void deveContarOsItensDoArmarioQueEstaoAVenda() {
        int id = 1;

        tested.apply(id);

        Mockito.verify(itemRepository).quantidadeItensAVendaArmario(id);
    }

}
