package br.com.cwi.crescer.api.repository.imagem;

import br.com.cwi.crescer.api.domain.Imagem;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ImagemRepository extends Repository<Imagem, Integer> {
    List<Imagem> findByIdItemIdItem(Integer id);
    Optional<Imagem> findByIdImagem(Integer id);

}
