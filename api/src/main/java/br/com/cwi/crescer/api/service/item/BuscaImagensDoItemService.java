package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.imagem.ImagemResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.mapper.imagem.ImagemResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaImagensDoItemService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private ImagemResponseMapper imagemResponseMapper;

    public List<ImagemResponse> apply(Integer idItem) {
        Item item = buscarItemPorIdService.find(idItem);
        return imagemResponseMapper.apply(item.getImagens());
    }
}
