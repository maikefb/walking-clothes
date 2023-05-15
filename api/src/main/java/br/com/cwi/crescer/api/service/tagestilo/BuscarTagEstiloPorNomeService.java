package br.com.cwi.crescer.api.service.tagestilo;

import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.tagestilo.TagEstiloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarTagEstiloPorNomeService {

    @Autowired
    private TagEstiloRepository repository;

    public TagEstilo apply(String tag) {
        Optional<TagEstilo> tagEstilo = repository.findByNomeIgnoringCase(tag);

        if (!tagEstilo.isPresent()) {
            throw new RegistroNaoEncontradoException("Tag");
        }

        return tagEstilo.get();
    }

}
