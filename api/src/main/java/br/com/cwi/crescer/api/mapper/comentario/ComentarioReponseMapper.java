package br.com.cwi.crescer.api.mapper.comentario;

import br.com.cwi.crescer.api.controller.response.CurtidaResponse;
import br.com.cwi.crescer.api.controller.response.comentario.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.mapper.curtida.CurtidaResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ComentarioReponseMapper {

    @Autowired
    private CurtidaResponseMapper curtidaResponseMapper;

    public ComentarioResponse apply(Comentario comentario) {
        ComentarioResponse response = new ComentarioResponse();
        response.setId(comentario.getIdComentario());
        response.setMensagem(comentario.getMensagem());
        response.setIdUsuario(comentario.getIdUsuario().getIdUsuario());
        response.setFotoPerfil(comentario.getIdUsuario().getFotoPerfil());
        List<CurtidaResponse> curtidas = comentario.getCurtidas().stream().map(curtida -> curtidaResponseMapper.convert(curtida)).collect(Collectors.toList());
        response.setCurtidas(curtidas);
        List<ComentarioResponse> lista = comentario.getComentarios().stream().map(c -> {
                    ComentarioResponse cr = apply(c);
                    cr.setResposta(true);
                    return cr;
                }
        ).collect(Collectors.toList());
        response.setComentarios(lista);
        return response;
    }
}
