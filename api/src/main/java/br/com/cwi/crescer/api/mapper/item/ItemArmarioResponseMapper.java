package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.service.imagem.ListarImagensItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemArmarioResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListarImagensItemService listarImagensItemService;

    public ItemArmarioResponse apply(Item item) {
        ItemArmarioResponse itemResponse = modelMapper.map(item, ItemArmarioResponse.class);
        List<String> imagens = listarImagensItemService.apply(item.getImagens());
        itemResponse.setImagens(imagens);
        return itemResponse;
    }

}
