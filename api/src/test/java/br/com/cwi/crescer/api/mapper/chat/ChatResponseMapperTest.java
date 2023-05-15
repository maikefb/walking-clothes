package br.com.cwi.crescer.api.mapper.chat;

import br.com.cwi.crescer.api.controller.response.chat.ChatResponse;
import br.com.cwi.crescer.api.controller.response.chat.MensagemResponse;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.domain.Mensagem;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.mapper.mensagem.MensagemResponseMapper;
import br.com.cwi.crescer.api.repository.mensagem.MensagemRepository;
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
public class ChatResponseMapperTest {

    @InjectMocks
    private ChatResponseMapper tested;

    @Mock
    private MensagemResponseMapper mensagemResponseMapper;

    @Mock
    private MensagemRepository mensagemRepository;

    @Test
    public void deveMapearOChatResponse() {
        Pedido pedido = new Pedido();
        Chat chat = new Chat();
        chat.setPedido(pedido);
        List<Mensagem> mensagens = new ArrayList<>();
        Mensagem mensagem = new Mensagem();
        mensagens.add(mensagem);
        MensagemResponse mensagemResponse = new MensagemResponse();

        Mockito.when(mensagemRepository.findByChatIdChatOrderByData(chat.getIdChat())).thenReturn(mensagens);
        Mockito.when(mensagemResponseMapper.apply(mensagem)).thenReturn(mensagemResponse);
        ChatResponse result = tested.apply(chat);

        Mockito.verify(mensagemRepository).findByChatIdChatOrderByData(chat.getIdChat());
        Mockito.verify(mensagemResponseMapper).apply(mensagem);
        Assert.assertEquals(chat.getIdChat(), result.getIdChat());
        Assert.assertEquals(chat.getPedido().getIdPedido(), result.getIdPedido());
    }
}
