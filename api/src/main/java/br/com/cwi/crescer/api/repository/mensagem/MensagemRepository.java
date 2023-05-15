package br.com.cwi.crescer.api.repository.mensagem;

import br.com.cwi.crescer.api.domain.Mensagem;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface MensagemRepository extends Repository<Mensagem, Integer> {
    List<Mensagem> findByChatIdChatOrderByData(Integer idChat);
}
