package br.com.cwi.crescer.api.repository.chat;

import br.com.cwi.crescer.api.domain.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
    Optional<Chat> findByIdChat(Integer id);
}
