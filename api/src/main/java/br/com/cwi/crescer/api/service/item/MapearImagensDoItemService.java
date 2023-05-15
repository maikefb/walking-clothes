package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapearImagensDoItemService {

    public List<Imagem> apply(List<String> imagens, Item item) {
        return imagens
                .stream()
                .map(
                        img -> new Imagem(img, item)
                )
                .collect(Collectors.toList());
    }

}
