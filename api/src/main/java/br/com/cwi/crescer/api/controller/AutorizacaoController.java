package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.usuario.CadastroUsuarioRequest;
import br.com.cwi.crescer.api.controller.request.usuario.LoginRequest;
import br.com.cwi.crescer.api.service.usuario.CadastrarUsuarioService;
import br.com.cwi.crescer.api.service.usuario.FazerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutorizacaoController {

    @Autowired
    private FazerLoginService fazerLoginService;

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@Valid @RequestBody LoginRequest loginRequest) {
       return fazerLoginService.login(loginRequest);
    }

    @PostMapping("/signup")
    public void cadastrarUsuario(@Valid @RequestBody CadastroUsuarioRequest cadastroUsuarioRequest) {
        cadastrarUsuarioService.register(cadastroUsuarioRequest);
    }
}
