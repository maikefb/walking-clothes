package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.dto.BuscaItemDto;
import br.com.cwi.crescer.api.mapper.imagem.ImagemResponseMapper;
import br.com.cwi.crescer.api.service.CalculaDistanciaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaItemDtoMapper {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ImagemResponseMapper imagemResponseMapper;

    @Autowired
    private CalculaDistanciaService calcula;

    public BuscaItemResponse apply(BuscaItemDto item, List<Imagem> imagens , double latitude, double longitude){
        BuscaItemResponse response = new BuscaItemResponse();
        response.setIdItem(item.getIdItem());
        response.setTitulo(item.getTitulo());
        response.setPreco(item.getPreco());
        response.setTamanho(item.getTamanho());
        response.setCidadeVendedor(item.getNome());
        response.setImagens(imagemResponseMapper.apply(imagens));
        response.setDistancia(calcula.apply(latitude, longitude, item.getLatitude(), item.getLongitude()));
        return response;
    }

}
