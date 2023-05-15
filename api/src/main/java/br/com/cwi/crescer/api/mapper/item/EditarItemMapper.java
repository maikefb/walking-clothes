package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.request.item.EditarItemRequest;
import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.service.armario.BuscarArmarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditarItemMapper {

    @Autowired
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    public Item apply(Item item, EditarItemRequest request) {
        item.setTitulo(request.getTitulo());
        item.setDescricao(request.getDescricao());
        item.setTamanho(request.getTamanho());
        item.setBusto(request.getBusto());
        item.setComprimento(request.getComprimento());
        item.setTipo(Tipo.valueOf(request.getTipo()));
        item.setCor(Cor.valueOf(request.getCor()));
        item.setEstadoUso(EstadoUso.valueOf(request.getEstadoUso()));
        item.setStatus(Status.D);
        item.setMarca(request.getMarca());
        item.setPrecoOriginal(request.getPrecoOriginal());
        item.setPreco(request.getPreco());
        item.setAceitaTroca(request.isAceitaTroca());
        Armario armario = buscarArmarioPorIdService.buscar(request.getIdNovoArmario());
        item.setIdArmario(armario);
        return item;
    }
}
