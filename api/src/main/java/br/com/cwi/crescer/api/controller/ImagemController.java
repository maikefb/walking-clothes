package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.imagem.AdicionarImagemRequest;
import br.com.cwi.crescer.api.controller.request.imagem.RemoverImagemRequest;
import br.com.cwi.crescer.api.service.imagem.AdicionarImagemAoItemService;
import br.com.cwi.crescer.api.service.imagem.RemoverImagemDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ImagemController {

    @Autowired
    private AdicionarImagemAoItemService adicionarImagemAoItemService;

    @Autowired
    private RemoverImagemDoItemService removerImagemDoItemService;

    @PostMapping("adicionar/imagem/item/{idItem}")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarImagemAoItem(@RequestBody AdicionarImagemRequest request, @PathVariable Integer idItem) {
        adicionarImagemAoItemService.add(request, idItem);
    }

    @DeleteMapping("remover/imagem/item/{idItem}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerImagemDoItem(@RequestBody RemoverImagemRequest request, @PathVariable Integer idItem) {
        removerImagemDoItemService.remover(request, idItem);
    }

}
