package br.com.cwi.crescer.api.mapper.comentario;

import br.com.cwi.crescer.api.controller.response.comentario.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ComentarioReponseMapperTest {

    @Test
    public void deveMapearOComentarioResponse() {
        ComentarioReponseMapper tested = new ComentarioReponseMapper();
        Comentario comentario = new Comentario();
        comentario.setIdUsuario(new Usuario());
        comentario.setCurtidas(new ArrayList<>());
        comentario.setComentarios(new ArrayList<>());
        List<Comentario> comentarios = new ArrayList<>();
        Comentario resposta = new Comentario();
        resposta.setIdUsuario(new Usuario());
        resposta.setCurtidas(new ArrayList<>());
        resposta.setComentarios(new ArrayList<>());
        comentarios.add(resposta);
        comentario.setComentarios(comentarios);

        ComentarioResponse result = tested.apply(comentario);

        Assert.assertEquals(comentario.getIdComentario(), result.getId());
        Assert.assertEquals(comentario.getMensagem(), result.getMensagem());
        Assert.assertEquals(comentario.getIdUsuario().getFotoPerfil(), result.getFotoPerfil());
    }
}
