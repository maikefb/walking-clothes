package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarArmarioPorIdService {

    @Autowired
    private ArmarioRepository armarioRepository;

    public Armario buscar(Integer idArmario) {
        Optional<Armario> armario = armarioRepository.findByIdArmario(idArmario);

        if (!armario.isPresent()) {
            throw new RegistroNaoEncontradoException("Armário");
        }

        return armario.get();
    }

}
