package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.response.comentario.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Comentario;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.comentario.ComentarioReponseMapper;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarComentarioPorItemServiceTest {

    @InjectMocks
    private BuscarComentarioPorItemService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private ComentarioReponseMapper comentarioReponseMapper;

    @Test
    public void deveBuscarOsComentariosDoItemDeIdInformado() {
        int id = 1;
        Item item = new Item();
        List<Comentario> comentarios = new ArrayList<>();
        Comentario comentario = new Comentario();
        comentarios.add(comentario);
        item.setComentarios(comentarios);
        ComentarioResponse response = new ComentarioResponse();

        Mockito.when(buscarItemPorIdService.find(id)).thenReturn(item);
        Mockito.when(comentarioReponseMapper.apply(comentario)).thenReturn(response);
        tested.find(id);

        Mockito.verify(comentarioReponseMapper).apply(comentario);
    }


}
