package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.endereco.EnderecoRequest;
import br.com.cwi.crescer.api.service.endereco.CadastrarOuEditarEnderecoService;
import br.com.cwi.crescer.api.service.endereco.PossuiEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnderecoController {

    @Autowired
    private CadastrarOuEditarEnderecoService cadastrarOuEditarEnderecoService;

    @Autowired
    private PossuiEnderecoService possuiEnderecoService;

    @PostMapping("cadastrar/endereco")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarEndereco(@RequestBody EnderecoRequest request) {
        cadastrarOuEditarEnderecoService.apply(request);
    }

    @PutMapping("editar/endereco")
    @ResponseStatus(HttpStatus.OK)
    public void editarEndereco(@RequestBody EnderecoRequest request) {
        cadastrarOuEditarEnderecoService.apply(request);
    }

    @GetMapping("endereco")
    @ResponseStatus(HttpStatus.OK)
    public boolean buscaEndereco(@RequestParam String email){
        return possuiEnderecoService.apply(email);
    }

}