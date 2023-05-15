package br.com.cwi.crescer.api.service.chat;

import br.com.cwi.crescer.api.controller.response.chat.ChatResponse;
import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.mapper.chat.ChatResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarChatService {
    @Autowired
    private BuscarChatPorIdService buscarChatPorIdService;

    @Autowired
    private ChatResponseMapper chatResponseMapper;

    public ChatResponse find(Integer id) {
        Chat chat = buscarChatPorIdService.find(id);
        return chatResponseMapper.apply(chat);
    }
}
