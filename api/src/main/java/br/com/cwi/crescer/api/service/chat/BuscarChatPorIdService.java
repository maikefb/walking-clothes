package br.com.cwi.crescer.api.service.chat;

import br.com.cwi.crescer.api.domain.Chat;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.chat.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarChatPorIdService {

    @Autowired
    private ChatRepository chatRepository;

    public Chat find(Integer id) {
        Optional<Chat> chat = chatRepository.findByIdChat(id);

        if (!chat.isPresent()) {
            throw new RegistroNaoEncontradoException("Chat");
        }

        return chat.get();
    }
}
