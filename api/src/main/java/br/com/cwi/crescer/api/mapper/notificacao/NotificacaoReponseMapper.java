package br.com.cwi.crescer.api.mapper.notificacao;

import br.com.cwi.crescer.api.controller.response.notificacao.NotificacaoResponse;
import br.com.cwi.crescer.api.domain.Notificacao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoReponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public NotificacaoResponse apply(Notificacao notificacao) {
        return modelMapper.map(notificacao, NotificacaoResponse.class);
    }
}
