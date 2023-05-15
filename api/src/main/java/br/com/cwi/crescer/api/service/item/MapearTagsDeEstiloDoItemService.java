package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.domain.TagItem;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.tagitem.TagItemRepository;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapearTagsDeEstiloDoItemService {

    @Autowired
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Autowired
    private TagItemRepository repository;

    public List<TagItem> apply(List<String> tags, Item item) {
        return tags
                .stream()
                .map(
                        nomeTag -> {
                            TagEstilo tagEstilo;

                            try {
                                tagEstilo = buscarTagEstiloPorNomeService.apply(nomeTag);
                            } catch (RegistroNaoEncontradoException exception) {
                                tagEstilo = new TagEstilo();
                            }
                            tagEstilo.setNome(nomeTag);

                            TagItem tagItem = new TagItem();
                            tagItem.setTagEstilo(tagEstilo);
                            tagItem.setIdItem(item);
                            repository.save(tagItem);

                            return tagItem;
                        }
                )
                .collect(Collectors.toList());
    }

}
