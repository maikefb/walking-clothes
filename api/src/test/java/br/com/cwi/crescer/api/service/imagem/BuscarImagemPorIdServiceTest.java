package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.imagem.ImagemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarImagemPorIdServiceTest {

    @InjectMocks
    private BuscarImagemPorIdService tested;

    @Mock
    private ImagemRepository imagemRepository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoImagemNaoForEncontrada() {
        int id = 0;

        tested.apply(id);

        Mockito.verify(imagemRepository).findByIdImagem(id);
    }

    @Test
    public void deveBuscarImagemPorId() {
        int id = 0;
        Imagem imagem = new Imagem();

        Mockito.when(imagemRepository.findByIdImagem(id)).thenReturn(Optional.of(imagem));
        Imagem result = tested.apply(id);

        Mockito.verify(imagemRepository).findByIdImagem(id);
        Assert.assertNotNull(result);
    }

}
