package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.request.armario.CadastrarArmarioRequest;
import br.com.cwi.crescer.api.controller.response.armario.CadastrarArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.TagEstilo;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarArmarioService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Autowired
    private ArmarioRepository armarioRepository;

    public CadastrarArmarioResponse cadastrar(CadastrarArmarioRequest request) {
        Usuario usuario = usuarioLogadoService.get();
        Armario armario = new Armario();

        armario.setNome(request.getNome());

        armario.setUsuario(usuario);

        TagEstilo tagEstilo;

        try {
            tagEstilo = buscarTagEstiloPorNomeService.apply(request.getTagEstilo());
        } catch (RegistroNaoEncontradoException exception) {
            tagEstilo = new TagEstilo();
            tagEstilo.setNome(request.getTagEstilo());
        }
        armario.setTagDeEstilo(tagEstilo);
        usuario.getArmarios().add(armario);
        armarioRepository.save(armario);

        return new CadastrarArmarioResponse(armario.getIdArmario(),armario.getNome());
    }

}
