package br.com.cwi.crescer.api.repository.endereco;

import br.com.cwi.crescer.api.domain.Endereco;
import org.springframework.data.repository.Repository;

public interface EnderecoRepository extends Repository<Endereco, Integer> {
    void save(Endereco endereco);
    boolean existsByIdEndereco(Integer idEndereco);
}
