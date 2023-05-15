package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.item.PaginaDoItemResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.item.PaginaItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarPaginaItemService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private PaginaItemMapper paginaItemMapper;

    public PaginaDoItemResponse find(Integer id) {
        Item item = buscarItemPorIdService.find(id);
        return paginaItemMapper.apply(item);
    }
}
