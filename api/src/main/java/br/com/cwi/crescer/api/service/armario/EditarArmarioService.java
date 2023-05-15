package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.request.armario.EditarArmarioRequest;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarArmarioService {

    @Autowired
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    @Autowired
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Autowired
    private ArmarioRepository repository;

    public void apply(EditarArmarioRequest request, Integer id) {
        Armario armario = buscarArmarioPorIdService.buscar(id);

        armario.setNome(request.getNome());

        TagEstilo tag;

        try {
            tag = buscarTagEstiloPorNomeService.apply(request.getTagDeEstilo());
        } catch (RegistroNaoEncontradoException exception) {
            tag = new TagEstilo();
            tag.setNome(request.getTagDeEstilo());
        }
        armario.setTagDeEstilo(tag);
        repository.save(armario);
    }

}
