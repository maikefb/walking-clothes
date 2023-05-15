package br.com.cwi.crescer.api.repository.usuario;

import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<Usuario> findByNome(String nome);

    Optional<Usuario> findByIdUsuario(Integer idUsuario);

}
