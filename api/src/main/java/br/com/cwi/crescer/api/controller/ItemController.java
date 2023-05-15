package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.comentario.ComentarRequest;
import br.com.cwi.crescer.api.controller.request.item.BuscaItemRequest;
import br.com.cwi.crescer.api.controller.request.item.CadastrarItemRequest;
import br.com.cwi.crescer.api.controller.request.item.EditarItemRequest;
import br.com.cwi.crescer.api.controller.response.BuscaItemPageResponse;
import br.com.cwi.crescer.api.controller.response.comentario.ComentarioResponse;
import br.com.cwi.crescer.api.controller.response.item.PaginaDoItemResponse;
import br.com.cwi.crescer.api.service.comentario.BuscarComentarioPorItemService;
import br.com.cwi.crescer.api.service.comentario.ComentarItemService;
import br.com.cwi.crescer.api.service.comentario.ResponderComentarioService;
import br.com.cwi.crescer.api.service.item.BuscaItemService;
import br.com.cwi.crescer.api.service.item.BuscarPaginaItemService;
import br.com.cwi.crescer.api.service.item.CadastrarItemService;
import br.com.cwi.crescer.api.service.item.EditarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ItemController {

    @Autowired
    private CadastrarItemService cadastrarItemService;

    @Autowired
    private BuscarPaginaItemService buscarPaginaItemService;

    @Autowired
    private ComentarItemService comentarItemService;

    @Autowired
    private ResponderComentarioService responderComentarioService;

    @Autowired
    private BuscarComentarioPorItemService buscarComentarioPorItemService;

    @Autowired
    private EditarItemService editarItemService;

    @Autowired
    private BuscaItemService buscaItemService;

    @PostMapping("cadastrar/item")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarItem(@RequestBody CadastrarItemRequest cadastrarItemRequest) {
        cadastrarItemService.cadastrar(cadastrarItemRequest);
    }
    @PostMapping("/item/public/buscar")
    @ResponseStatus(HttpStatus.OK)
    public BuscaItemPageResponse buscarItem(@RequestBody BuscaItemRequest buscaItemRequest) {
        return buscaItemService.find(buscaItemRequest);
    }
    @GetMapping("public/item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaginaDoItemResponse exibirPaginaItem(@PathVariable Integer id) {
        return buscarPaginaItemService.find(id);
    }

    @PostMapping("item/comentario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void comentar(@RequestBody ComentarRequest comentarioRequest, @PathVariable Integer id) {
        comentarItemService.comentar(comentarioRequest, id);
    }
    @GetMapping("public/comentarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ComentarioResponse> buscarComentariosPorItemId(@PathVariable Integer id) {
        return buscarComentarioPorItemService.find(id);
    }
    @PutMapping("editar/item/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarItem(@RequestBody EditarItemRequest editarItemRequest, @PathVariable Integer id) {
        editarItemService.apply(editarItemRequest, id);
    }
}
