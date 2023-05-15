package br.com.cwi.crescer.api.service.chat;

import br.com.cwi.crescer.api.controller.response.chat.ChatResponse;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.mapper.chat.ChatResponseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BuscarChatServiceTest {

    @InjectMocks
    private BuscarChatService tested;

    @Mock
    private BuscarChatPorIdService buscarChatPorIdService;

    @Mock
    private ChatResponseMapper chatResponseMapper;

    @Test
    public void deveRetornarOChatResponse() {
        int id = 1;
        Chat chat = new Chat();
        ChatResponse chatResponse = new ChatResponse();

        Mockito.when(buscarChatPorIdService.find(id)).thenReturn(chat);
        Mockito.when(chatResponseMapper.apply(chat)).thenReturn(chatResponse);
        tested.find(id);

        Mockito.verify(buscarChatPorIdService).find(id);
        Mockito.verify(chatResponseMapper).apply(chat);
    }

}
