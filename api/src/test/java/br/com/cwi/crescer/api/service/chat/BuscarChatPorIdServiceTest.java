package br.com.cwi.crescer.api.service.chat;

import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.chat.ChatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarChatPorIdServiceTest {

    @InjectMocks
    private BuscarChatPorIdService tested;

    @Mock
    private ChatRepository chatRepository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoChatNaoForEncontrado() {
        int id = 1;

        tested.find(id);

        Mockito.verify(chatRepository).findByIdChat(id);
    }

    @Test
    public void deveRetornarChatEncontrado() {
        int id = 1;
        Chat chat = new Chat();

        Mockito.when(chatRepository.findByIdChat(id)).thenReturn(Optional.of(chat));
        Chat result = tested.find(id);

        Mockito.verify(chatRepository).findByIdChat(id);
        Assert.assertNotNull(result);
    }

}
