package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.controller.request.imagem.RemoverImagemRequest;
import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoverImagemDoItemService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private BuscarImagemPorIdService buscarImagemPorIdService;

    @Autowired
    private ItemRepository itemRepository;

    public void remover(RemoverImagemRequest request, Integer idItem) {
        Item item = buscarItemPorIdService.find(idItem);
        Imagem imagem = buscarImagemPorIdService.apply(request.getIdImagem());
        item.getImagens().remove(imagem);
        itemRepository.save(item);
    }

}
