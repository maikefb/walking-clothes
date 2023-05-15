package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.armario.BuscaArmarioRequest;
import br.com.cwi.crescer.api.controller.request.armario.CadastrarArmarioRequest;
import br.com.cwi.crescer.api.controller.request.armario.EditarArmarioRequest;
import br.com.cwi.crescer.api.controller.response.armario.*;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.service.armario.*;
import br.com.cwi.crescer.api.service.item.BuscarItensFavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ArmarioController {
    @Autowired
    private CadastrarArmarioService cadastrarArmarioService;

    @Autowired
    private ExibirPaginaDoArmarioService exibirPaginaDoArmarioService;


    @Autowired
    private BuscarArmariosUsuarioService buscarArmariosUsuarioService;

    @Autowired
    private EditarArmarioService editarArmarioService;

    @Autowired
    private AdicionarAosFavoritosService adicionarAosFavoritosService;

    @Autowired
    private BuscarItensFavoritosService buscarItensArmarioFavoritoService;

    @Autowired
    private BuscaArmariosService buscaArmariosService;

    @Autowired
    private BuscarOpcoesArmarioService buscarOpcoesArmarioService;

    @PostMapping("cadastrar/armario")
    @ResponseStatus(HttpStatus.CREATED)
    public CadastrarArmarioResponse cadastrarArmario(@RequestBody CadastrarArmarioRequest cadastrarArmarioRequest) {
        return cadastrarArmarioService.cadastrar(cadastrarArmarioRequest);
    }

    @GetMapping("public/armario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BuscarArmarioResponse exibirPaginaDoArmario(@PathVariable Integer id) {
        return exibirPaginaDoArmarioService.apply(id);
    }

    @GetMapping("public/armario/{idUsuario}/{pagina}")
    @ResponseStatus(HttpStatus.OK)
    public List<BuscarArmarioResponse> buscarArmarioPorId(@PathVariable Integer idUsuario, @PathVariable Integer pagina) {
        return buscarArmariosUsuarioService.buscar(idUsuario, pagina);
    }

    @PutMapping("editar/armario/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editarArmario(@RequestBody EditarArmarioRequest request, @PathVariable Integer id) {
        editarArmarioService.apply(request, id);
    }

    @PostMapping("favoritos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void adicionarAosFavoritos(@PathVariable Integer id) {
        adicionarAosFavoritosService.add(id);
    }

    @GetMapping("favoritos")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemArmarioResponse> buscarFavoritos() {
        return buscarItensArmarioFavoritoService.find();
    }


    @PostMapping("public/armario")
    @ResponseStatus(HttpStatus.OK)
    public BuscaArmarioPageResponse findAllByFilter(@RequestBody BuscaArmarioRequest request) {
        return buscaArmariosService.find(request);

    }

    @GetMapping("buscar/armarios")
    @ResponseStatus(HttpStatus.OK)
    public List<OpcoesArmarioResponse> buscarOpcoesArmario() {
        return buscarOpcoesArmarioService.buscar();
    }

}
