package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.request.item.EditarItemRequest;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.item.EditarItemMapper;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarItemService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private EditarItemMapper editarItemMapper;

    @Autowired
    private ItemRepository repository;

    public void apply(EditarItemRequest request, Integer id) {
        Item item = buscarItemPorIdService.find(id);
        editarItemMapper.apply(item, request);
        repository.save(item);
    }

}
