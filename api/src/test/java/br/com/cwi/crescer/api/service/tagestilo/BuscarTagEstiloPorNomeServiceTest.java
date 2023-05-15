package br.com.cwi.crescer.api.service.tagestilo;

import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.tagestilo.TagEstiloRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarTagEstiloPorNomeServiceTest {

    @InjectMocks
    private BuscarTagEstiloPorNomeService tested;

    @Mock
    private TagEstiloRepository repository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoTagNaoForEncontrada() {
        String tag = "";

        tested.apply(tag);

        Mockito.verify(repository).findByNomeIgnoringCase(tag);
    }

    @Test
    public void deveRetornarTagQuandoEncontrada() {
        String tag = "";
        TagEstilo tagEstilo = new TagEstilo();

        Mockito.when(repository.findByNomeIgnoringCase(tag)).thenReturn(Optional.of(tagEstilo));
        TagEstilo result = tested.apply(tag);

        Mockito.verify(repository).findByNomeIgnoringCase(tag);
        Assert.assertNotNull(result);
    }

}
