package br.com.cwi.crescer.api.mapper.mensagem;

import br.com.cwi.crescer.api.controller.response.chat.MensagemResponse;
import br.com.cwi.crescer.api.domain.Mensagem;
import br.com.cwi.crescer.api.mapper.usuario.BuscarUsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MensagemResponseMapper {
    @Autowired
    private BuscarUsuarioMapper buscarUsuarioMapper;

    public MensagemResponse apply(Mensagem mensagem) {
        MensagemResponse response = new MensagemResponse();
        response.setIdChat(mensagem.getChat().getIdChat());
        response.setMensagem(mensagem.getConteudo());
        response.setRemetente(buscarUsuarioMapper.apply(mensagem.getRemetente()));
        response.setDestinatario(buscarUsuarioMapper.apply(mensagem.getDestinatario()));
        response.setData(mensagem.getData());
        return response;
    }
}
