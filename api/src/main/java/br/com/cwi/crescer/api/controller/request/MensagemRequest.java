package br.com.cwi.crescer.api.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemRequest {
    private Integer idUsuario;
    private Integer idChat;
    private String message;
}
