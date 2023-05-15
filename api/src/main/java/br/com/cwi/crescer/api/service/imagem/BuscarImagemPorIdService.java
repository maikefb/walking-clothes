package br.com.cwi.crescer.api.service.imagem;

import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.imagem.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarImagemPorIdService {

    @Autowired
    private ImagemRepository imagemRepository;

    public Imagem apply(Integer id) {
        Optional<Imagem> imagem = imagemRepository.findByIdImagem(id);
        if (!imagem.isPresent()) {
            throw new RegistroNaoEncontradoException("Imagem");
        }

        return imagem.get();
    }

}

