package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.usuario.EdicaoPerfilRequest;
import br.com.cwi.crescer.api.controller.response.PossuiEnderecoResponse;
import br.com.cwi.crescer.api.controller.response.notificacao.NotificacaoResponse;
import br.com.cwi.crescer.api.controller.response.usuario.BuscarUsuarioResponse;
import br.com.cwi.crescer.api.controller.response.usuario.PerfilUsuarioResponse;
import br.com.cwi.crescer.api.service.notificacao.BuscarNotificacaoService;
import br.com.cwi.crescer.api.service.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private ExibirPerfilUsuarioService exibirPerfilUsuarioService;

    @Autowired
    private BuscarUsuarioPorNomeService buscarUsuarioPorNomeService;

    @Autowired
    private BuscarNotificacaoService buscarNotificacaoService;

    @Autowired
    private EditarPerfilService editarPerfilService;

    @Autowired
    private VerificarSeUsuarioPossuiEnderecoCadastradoService verificarSeUsuarioPossuiEnderecoCadastradoService;

    @Autowired
    private ExibirPerfilUsuarioLogadoService exibirPerfilUsuarioLogadoService;
    @Autowired
    private BuscarInfoPerfilService buscarInfoPerfilService;

    @GetMapping("public/usuario/{id}/{pagina}")
    @ResponseStatus(HttpStatus.OK)
    public PerfilUsuarioResponse exibirPerfilUsuarioPorId(@PathVariable Integer id, @PathVariable Integer pagina) {
        return exibirPerfilUsuarioService.apply(id, pagina);
    }

    @GetMapping("public/buscar/usuario/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<BuscarUsuarioResponse> buscarUsuario(@PathVariable String nome) {
        return buscarUsuarioPorNomeService.buscar(nome);
    }

    @GetMapping("usuario/info")
    @ResponseStatus(HttpStatus.OK)
    public BuscarUsuarioResponse buscarInfo() {
        return buscarInfoPerfilService.find();
    }

    @GetMapping("notificacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificacaoResponse> buscarNotificoes() {
        return buscarNotificacaoService.find();
    }

    @PutMapping("editar-perfil")
    @ResponseStatus(HttpStatus.OK)
    public void editarPerfil(@RequestBody EdicaoPerfilRequest request) {
        editarPerfilService.editar(request);
    }

    @GetMapping("/usuario/verificar-possui-endereco")
    @ResponseStatus(HttpStatus.OK)
    public PossuiEnderecoResponse verificarSeUsuarioPossuiEnderecoCadastrado() {
        return verificarSeUsuarioPossuiEnderecoCadastradoService.apply();
    }

    @GetMapping("usuario/meu-perfil/{pagina}")
    @ResponseStatus(HttpStatus.OK)
    public PerfilUsuarioResponse exibirPerfilUsuarioLogado(@PathVariable Integer pagina) {
        return exibirPerfilUsuarioLogadoService.apply(pagina);
    }

}
