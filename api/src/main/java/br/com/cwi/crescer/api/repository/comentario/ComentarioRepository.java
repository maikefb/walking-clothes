package br.com.cwi.crescer.api.repository.comentario;

import br.com.cwi.crescer.api.domain.Comentario;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ComentarioRepository  extends PagingAndSortingRepository<Comentario, Integer> {

}
