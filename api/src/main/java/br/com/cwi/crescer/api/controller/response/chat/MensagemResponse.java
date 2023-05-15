package br.com.cwi.crescer.api.controller.response.chat;

import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MensagemResponse {
    private Integer idChat;
    private BuscarUsuarioResponse remetente;
    private BuscarUsuarioResponse destinatario;
    private String mensagem;
    private LocalDateTime data;
}
