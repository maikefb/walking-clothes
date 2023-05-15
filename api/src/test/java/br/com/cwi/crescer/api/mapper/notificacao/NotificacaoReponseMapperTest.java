package br.com.cwi.crescer.api.mapper.notificacao;

import br.com.cwi.crescer.api.controller.response.notificacao.NotificacaoResponse;
import br.com.cwi.crescer.api.domain.Notificacao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class NotificacaoReponseMapperTest {

    @InjectMocks
    private NotificacaoReponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearONotificacaoResponse() {
        Notificacao notificacao = new Notificacao();
        NotificacaoResponse notificacaoResponse = new NotificacaoResponse();

        Mockito.when(modelMapper.map(notificacao, NotificacaoResponse.class)).thenReturn(notificacaoResponse);
        NotificacaoResponse result = tested.apply(notificacao);

        Mockito.verify(modelMapper).map(notificacao, NotificacaoResponse.class);
        Assert.assertEquals(notificacao.getIdNotificacao(), result.getIdNotificacao());
        Assert.assertEquals(notificacao.getMensagem(), result.getMensagem());
        Assert.assertEquals(notificacao.isIsativa(), result.isIsativa());
    }

}
