package br.com.cwi.crescer.api.service.chat;

import br.com.cwi.crescer.api.controller.request.MensagemRequest;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.domain.Mensagem;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.chat.ChatRepository;
import br.com.cwi.crescer.api.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EnviarMensagemService {

    @Autowired
    private BuscarChatPorIdService buscarChatPorIdService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public void send(MensagemRequest request) {
        Chat chat = buscarChatPorIdService.find(request.getIdChat());
        Mensagem mensagem = new Mensagem();
        Usuario usuario = buscarUsuarioPorIdService.apply(request.getIdUsuario());
        mensagem.setRemetente(usuario);
        if (usuario.getIdUsuario().equals(chat.getPedido().getVendedor().getIdUsuario())) {
            mensagem.setDestinatario(chat.getPedido().getComprador());
        } else {
            mensagem.setDestinatario(chat.getPedido().getVendedor());
        }
        mensagem.setData(LocalDateTime.now());
        mensagem.setConteudo(request.getMessage());
        mensagem.setChat(chat);
        chat.getMensagens().add(mensagem);
        chatRepository.save(chat);
    }
}
