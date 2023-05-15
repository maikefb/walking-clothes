package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuscarItemMapper {

    @Autowired
    private ModelMapper modelMapper;

    public BuscaItemResponse apply(Item item) {
        return modelMapper.map(item, BuscaItemResponse.class);
    }

}
