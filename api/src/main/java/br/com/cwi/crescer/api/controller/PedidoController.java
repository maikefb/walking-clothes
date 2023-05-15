package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.pedido.PedidoRequest;
import br.com.cwi.crescer.api.controller.response.chat.ChatResponse;
import br.com.cwi.crescer.api.controller.response.pedido.PedidoResponse;
import br.com.cwi.crescer.api.service.chat.BuscarChatService;
import br.com.cwi.crescer.api.service.pedido.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {
    @Autowired
    private BuscarChatService buscarChatService;

    @Autowired
    private BuscarPedidoService buscarPedidoService;

    @Autowired
    private RealizarPedidoService realizarPedidoService;

    @Autowired
    private CancelarPedidoService cancelarPedidoService;

    @Autowired
    private FinalizarPedidoService finalizarPedidoService;

    @Autowired
    private RemoverItemPedidoService removerItemPedidoService;

    @Autowired
    private BuscarVendasService buscarVendasService;

    @Autowired
    private BuscarPedidosInativosService buscarPedidosInativosService;
    @Autowired
    private BuscarPedidoResponsePorIdService buscarPedidoResponsePorIdService;

    @GetMapping("chat/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChatResponse buscarChat(@PathVariable Integer id) {
        return buscarChatService.find(id);
    }

    @GetMapping("pedidos")
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponse> buscarPedidos() {
        return buscarPedidoService.get();
    }

    @GetMapping("pedido/buscar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PedidoResponse buscarPedidoPorId(@PathVariable Integer id) {
        return buscarPedidoResponsePorIdService.find(id);
    }

    @GetMapping("pedidos-inativos")
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponse> buscarPedidosInativos() {
        return buscarPedidosInativosService.get();
    }

    @GetMapping("vendas")
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoResponse> buscarVendas() {
        return buscarVendasService.get();
    }

    @PostMapping("realizar-pedido")
    @ResponseStatus(HttpStatus.OK)
    public void realizarPedido(@RequestBody PedidoRequest pedido) {
        realizarPedidoService.realizar(pedido);
    }

    @DeleteMapping("cancelar-pedido/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void cancelarPedido(@PathVariable Integer id) {
        cancelarPedidoService.cancelar(id);
    }

    @PutMapping("finalizar-pedido/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void finalizarPedido(@PathVariable Integer id) {
        finalizarPedidoService.finalizar(id);
    }

    @PatchMapping("remover-item/{idItem}/{idPedido}")
    @ResponseStatus(HttpStatus.OK)
    public void removerItemPedido(@PathVariable Integer idItem,@PathVariable Integer idPedido) {
        removerItemPedidoService.remover(idItem,idPedido);
    }

}
