package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContarItensAVendaUsuarioServiceTest {

    @InjectMocks
    private ContarItensAVendaUsuarioService tested;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void deveContarItensAVendaDoUsuario() {
        int idUsuario = 1;

        tested.apply(idUsuario);

        Mockito.verify(itemRepository).quantidadeItensAVendaUsuario(idUsuario);
    }

}
