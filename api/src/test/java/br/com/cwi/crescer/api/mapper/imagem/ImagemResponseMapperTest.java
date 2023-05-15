package br.com.cwi.crescer.api.mapper.imagem;

import br.com.cwi.crescer.api.controller.response.imagem.ImagemResponse;
import br.com.cwi.crescer.api.domain.Imagem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ImagemResponseMapperTest {

    @InjectMocks
    private ImagemResponseMapper tested;

    @Mock
    private ModelMapper mapper;

    @Test
    public void deveMapearAsImagensResponse() {
        List<Imagem> imagens = new ArrayList<>();
        Imagem imagem = new Imagem();
        imagens.add(imagem);
        List<ImagemResponse> imagemResponses = new ArrayList<>();
        ImagemResponse imagemResponse = new ImagemResponse();
        imagemResponses.add(imagemResponse);

        Mockito.when(mapper.map(imagem, ImagemResponse.class)).thenReturn(imagemResponse);
        List<ImagemResponse> result = tested.apply(imagens);

        Mockito.verify(mapper).map(imagem, ImagemResponse.class);
        Assert.assertEquals(imagem.getUrlImagem(), imagemResponse.getUrlImagem());
        Assert.assertSame(result.get(0), imagemResponses.get(0));
    }
}
