package br.com.cwi.crescer.api.repository.municipio;

import br.com.cwi.crescer.api.domain.Municipio;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MunicipioRepository extends Repository<Municipio,Integer> {
    Optional<Municipio> findByNome(String nome);
}
