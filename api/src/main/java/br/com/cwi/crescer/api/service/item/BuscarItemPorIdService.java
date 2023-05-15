package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.mapper.item.BuscarItemMapper;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarItemPorIdService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private BuscarItemMapper buscarItemMapper;

    public Item find(Integer id) {
        Optional<Item> item = repository.findByIdItem(id);
        if (!item.isPresent()) {
            throw new RegistroNaoEncontradoException("Item");
        }
        return item.get();
    }

}
