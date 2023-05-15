package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.response.comentario.ComentarioResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.comentario.ComentarioReponseMapper;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarComentarioPorItemService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private ComentarioReponseMapper comentarioReponseMapper;

    public List<ComentarioResponse> find(Integer id) {
        Item item = buscarItemPorIdService.find(id);
        return item.getComentarios().stream().map(c -> comentarioReponseMapper.apply(c)).collect(Collectors.toList());
    }
}
