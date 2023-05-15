package br.com.cwi.crescer.api.service.municipio;

import br.com.cwi.crescer.api.domain.Municipio;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.municipio.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarMunicipioPorNomeService {

    @Autowired
    private MunicipioRepository repository;

    public Municipio apply(String nome) {
        Optional<Municipio> municipio = repository.findByNome(nome);

        if (!municipio.isPresent()) {
            throw new RegistroNaoEncontradoException("Município");
        }

        return municipio.get();
    }

}
