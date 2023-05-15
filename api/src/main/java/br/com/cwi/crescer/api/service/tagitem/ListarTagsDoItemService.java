package br.com.cwi.crescer.api.service.tagitem;

import br.com.cwi.crescer.api.domain.TagEstilo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarTagsDoItemService {

    public List<String> apply(List<TagEstilo> tags) {
        return tags
                .stream()
                .map(TagEstilo::getNome)
                .collect(Collectors.toList());
    }

}
