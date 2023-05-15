package br.com.cwi.crescer.api.controller.response.comentario;

import br.com.cwi.crescer.api.controller.response.CurtidaResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComentarioResponse {

    private Integer id;
    private Integer idUsuario;
    private String fotoPerfil;
    private String mensagem;
    private List<CurtidaResponse> curtidas;
    private List<ComentarioResponse> comentarios;
    private boolean isResposta;
}
