package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.request.item.CadastrarItemRequest;
import br.com.cwi.crescer.api.domain.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CadastrarItemRequestMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Item apply(CadastrarItemRequest request) {
        Item item = modelMapper.map(request, Item.class);
        item.setBusto(BigDecimal.valueOf(request.getBusto()));
        item.setComprimento(BigDecimal.valueOf(request.getComprimento()));
        item.setQuadril(BigDecimal.valueOf(request.getQuadril()));
        item.setTipo(Tipo.valueOf(request.getTipo()));
        item.setCor(Cor.valueOf(request.getCor()));
        item.setEstadoUso(EstadoUso.valueOf(request.getEstadoUso()));
        item.setStatus(Status.D);
        item.setPreco(BigDecimal.valueOf(request.getPreco()));
        item.setPrecoOriginal(BigDecimal.valueOf(request.getPrecoOriginal()));
        return item;
    }
}
