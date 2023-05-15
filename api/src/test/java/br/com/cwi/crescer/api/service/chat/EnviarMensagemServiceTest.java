package br.com.cwi.crescer.api.service.chat;

import br.com.cwi.crescer.api.controller.request.MensagemRequest;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.domain.Pedido;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.chat.ChatRepository;
import br.com.cwi.crescer.api.service.usuario.BuscarUsuarioPorIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EnviarMensagemServiceTest {

    @InjectMocks
    private EnviarMensagemService tested;

    @Mock
    private BuscarChatPorIdService buscarChatPorIdService;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Test
    public void deveEnviarMensagemNoChatAoComprador() {
        MensagemRequest request = new MensagemRequest();
        request.setIdChat(1);
        request.setIdUsuario(1);
        int id = 1;
        Usuario vendedor = new Usuario();
        Pedido pedido = new Pedido();
        pedido.setVendedor(vendedor);
        Chat chat = new Chat();
        chat.setPedido(pedido);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);

        Mockito.when(buscarChatPorIdService.find(id)).thenReturn(chat);
        Mockito.when(buscarUsuarioPorIdService.apply(request.getIdChat())).thenReturn(usuario);
        Mockito.when(chatRepository.save(chat)).thenReturn(chat);
        tested.send(request);

        Mockito.verify(buscarChatPorIdService).find(id);
        Mockito.verify(buscarUsuarioPorIdService).apply(request.getIdChat());
        Mockito.verify(chatRepository).save(chat);
    }

    @Test
    public void deveEnviarMensagemNoChatAoVendedor() {
        MensagemRequest request = new MensagemRequest();
        request.setIdChat(1);
        request.setIdUsuario(1);
        int id = 1;
        Usuario vendedor = new Usuario();
        vendedor.setIdUsuario(1);
        Pedido pedido = new Pedido();
        pedido.setVendedor(vendedor);
        Chat chat = new Chat();
        chat.setPedido(pedido);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);

        Mockito.when(buscarChatPorIdService.find(id)).thenReturn(chat);
        Mockito.when(buscarUsuarioPorIdService.apply(request.getIdChat())).thenReturn(usuario);
        Mockito.when(chatRepository.save(chat)).thenReturn(chat);
        tested.send(request);

        Mockito.verify(buscarChatPorIdService).find(id);
        Mockito.verify(buscarUsuarioPorIdService).apply(request.getIdChat());
        Mockito.verify(chatRepository).save(chat);
    }

}
