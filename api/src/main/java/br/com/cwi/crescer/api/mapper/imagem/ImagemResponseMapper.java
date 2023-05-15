package br.com.cwi.crescer.api.mapper.imagem;

import br.com.cwi.crescer.api.controller.response.imagem.ImagemResponse;
import br.com.cwi.crescer.api.domain.Imagem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImagemResponseMapper {

    @Autowired
    private ModelMapper mapper;

    public List<ImagemResponse> apply(List<Imagem> imagem) {
        return imagem
                .stream()
                .map(img -> mapper.map(img, ImagemResponse.class))
                .collect(Collectors.toList());
    }
}
