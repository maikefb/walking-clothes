package br.com.cwi.crescer.api.service.pedido;

import br.com.cwi.crescer.api.controller.request.pedido.PedidoRequest;
import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import br.com.cwi.crescer.api.service.usuario.BuscarUsuarioPorIdService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RealizarPedidoService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public void realizar(PedidoRequest pedidoRequest) {
        Usuario usuario = usuarioLogadoService.get();
        Pedido pedido = new Pedido();
        pedido.setAtivo(true);
        pedido.setComprador(usuario);
        pedido.setData(LocalDate.now());
        List<Item> itens = pedidoRequest.getIds().stream().map(id -> {
            Item item = buscarItemPorIdService.find(id);
            item.setStatus(Status.N);
            return item;
        }).collect(Collectors.toList());

        Chat chat = new Chat();
        chat.setPedido(pedido);
        pedido.setChat(chat);
        pedido.setItens(itens);

        Usuario vendedor = buscarUsuarioPorIdService.apply(pedidoRequest.getIdVendedor());
        pedido.setVendedor(vendedor);
        usuario.getPedidos().add(pedido);
        Notificacao notificacao = new Notificacao();
        notificacao.setIsativa(true);
        notificacao.setIdUsuario(vendedor);
        notificacao.setMensagem(String.format("%s deseja comprar/trocar item(ns) com você!", usuario.getNome()));

        usuarioRepository.save(usuario);
    }
}
