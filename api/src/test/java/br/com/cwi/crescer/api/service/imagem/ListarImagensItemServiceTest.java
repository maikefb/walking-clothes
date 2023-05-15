package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.domain.Imagem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ListarImagensItemServiceTest {

    @InjectMocks
    private ListarImagensItemService tested;

    @Test
    public void deveListarImagensDoItem() {
        List<Imagem> imagens = new ArrayList<>();

        List<String> listaImagens = tested.apply(imagens);

        Assert.assertNotNull(listaImagens);
    }

}
