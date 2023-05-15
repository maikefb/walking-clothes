package br.com.cwi.crescer.api.repository.tagestilo;

import br.com.cwi.crescer.api.domain.TagEstilo;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface TagEstiloRepository extends Repository<TagEstilo, Integer> {
    Optional<TagEstilo> findByNomeIgnoringCase(String nome);

    void save(TagEstilo tagEstilo);
}
