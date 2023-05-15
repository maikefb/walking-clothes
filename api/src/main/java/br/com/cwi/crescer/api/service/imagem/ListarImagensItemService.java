package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.domain.Imagem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarImagensItemService {

    public List<String> apply(List<Imagem> imagens) {
        return imagens
                .stream()
                .map(Imagem::getUrlImagem)
                .collect(Collectors.toList());
    }

}
