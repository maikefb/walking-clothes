package br.com.cwi.crescer.api.mapper.mensagem;

import br.com.cwi.crescer.api.controller.response.chat.MensagemResponse;
import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.domain.Mensagem;
import br.com.cwi.crescer.api.mapper.usuario.BuscarUsuarioMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MensagemResponseMapperTest {

    @InjectMocks
    private MensagemResponseMapper tested;

    @Mock
    private BuscarUsuarioMapper buscarUsuarioMapper;

    @Test
    public void deveMapearOMensagemResponse() {
        Mensagem mensagem = new Mensagem();
        Chat chat = new Chat();
        mensagem.setChat(chat);
        BuscarUsuarioResponse usuario = new BuscarUsuarioResponse();

        Mockito.when(buscarUsuarioMapper.apply(mensagem.getRemetente())).thenReturn(usuario);
        MensagemResponse result = tested.apply(mensagem);

        Mockito.verify(buscarUsuarioMapper, Mockito.times(2)).apply(mensagem.getRemetente());
        Assert.assertEquals(mensagem.getChat().getIdChat(), result.getIdChat());
        Assert.assertEquals(mensagem.getConteudo(), result.getMensagem());
        Assert.assertEquals(mensagem.getData(), result.getData());
    }
}
