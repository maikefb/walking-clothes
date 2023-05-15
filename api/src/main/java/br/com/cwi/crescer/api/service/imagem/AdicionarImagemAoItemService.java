package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.controller.request.imagem.AdicionarImagemRequest;
import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarImagemAoItemService {

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private ItemRepository itemRepository;

    public void add(AdicionarImagemRequest request, Integer idItem) {
        Item item = buscarItemPorIdService.find(idItem);
        Imagem imagem = new Imagem(request.getUrlImagem(), item);
        item.getImagens().add(imagem);
        itemRepository.save(item);
    }

}
