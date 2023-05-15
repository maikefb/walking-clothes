package br.com.cwi.crescer.api.controller.response.chat;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatResponse {

    private Integer idChat;
    private Integer idPedido;
    private List<MensagemResponse> mensagens;
}
