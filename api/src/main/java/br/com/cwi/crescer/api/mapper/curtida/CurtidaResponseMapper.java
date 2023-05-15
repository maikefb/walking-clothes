package br.com.cwi.crescer.api.mapper.curtida;

import br.com.cwi.crescer.api.controller.response.CurtidaResponse;
import br.com.cwi.crescer.api.domain.Curtida;
import org.springframework.stereotype.Component;

@Component
public class CurtidaResponseMapper {
    public CurtidaResponse convert(Curtida curtida){
        CurtidaResponse response = new CurtidaResponse();
        response.setIdUsuario(curtida.getUsuario().getIdUsuario());
        return response;
    }
}
