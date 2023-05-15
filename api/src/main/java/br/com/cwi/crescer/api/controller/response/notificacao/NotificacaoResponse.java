package br.com.cwi.crescer.api.controller.response.notificacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoResponse {
    private Integer idNotificacao;
    private String mensagem;
    private boolean isativa;
}
