package br.com.cwi.crescer.api.mapper.chat;

import br.com.cwi.crescer.api.controller.response.chat.ChatResponse;
import br.com.cwi.crescer.api.controller.response.chat.MensagemResponse;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.mapper.mensagem.MensagemResponseMapper;
import br.com.cwi.crescer.api.repository.mensagem.MensagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChatResponseMapper {

    @Autowired
    private MensagemResponseMapper mensagemResponseMapper;

    @Autowired
    private MensagemRepository mensagemRepository;

    public ChatResponse apply(Chat chat) {
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setIdChat(chat.getIdChat());
        chatResponse.setIdPedido(chat.getPedido().getIdPedido());
        List<MensagemResponse> mensagens =
                mensagemRepository.findByChatIdChatOrderByData(chatResponse.getIdChat())
                        .stream()
                        .map(mensagem ->
                                mensagemResponseMapper.apply(mensagem))
                        .collect(Collectors.toList());
        chatResponse.setMensagens(mensagens);
        return chatResponse;
    }
}
