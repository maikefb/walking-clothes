package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.request.item.BuscaItemRequest;
import br.com.cwi.crescer.api.controller.response.BuscaItemPageResponse;
import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.dto.BuscaItemDto;
import br.com.cwi.crescer.api.mapper.item.BuscaItemDtoMapper;
import br.com.cwi.crescer.api.repository.imagem.ImagemRepository;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuscaItemService {
    private static final int QUANTIDADE_ITENS_POR_PAGINA = 20;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private BuscaItemDtoMapper mapper;

    public BuscaItemPageResponse find(BuscaItemRequest request) {
        Pageable pageable = PageRequest.of(request.getPagePost(), QUANTIDADE_ITENS_POR_PAGINA);

        double latitude = (request.getLatitude() * Math.PI / 180);
        double longitude = (request.getLongitude() * Math.PI / 180);

        List<BuscaItemResponse> response = new ArrayList<>();
        Page<BuscaItemDto> itens = itemRepository.findByFilter(request.getNome(), request.getTamanho(), request.getTipo(),
                request.getCor(), request.getEstadoUso(), request.getPrecoIni(), request.getPreco(), request.getAceitaTroca(), latitude, longitude, request.getRaio(), pageable);
        for (BuscaItemDto item : itens) {
            List<Imagem> imagens = imagemRepository.findByIdItemIdItem(item.getIdItem());
            response.add(mapper.apply(item, imagens, request.getLatitude(), request.getLongitude()));
        }

        return new BuscaItemPageResponse(response, itens.getNumber(), itens.getTotalPages());

    }


}
