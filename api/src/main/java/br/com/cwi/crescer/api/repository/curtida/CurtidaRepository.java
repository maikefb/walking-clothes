package br.com.cwi.crescer.api.repository.curtida;

import br.com.cwi.crescer.api.domain.Curtida;
import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurtidaRepository extends CrudRepository<Curtida, Integer> {
    Curtida save(Curtida curtida);

    @Query("Select c FROM Curtida c where c.usuario = ?1 and c.comentario.idComentario = ?2")
    Optional<Curtida> findCurtidaByUsuario(Usuario usuario, Integer id);
}
