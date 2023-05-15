package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.request.item.BuscaItemRequest;
import br.com.cwi.crescer.api.controller.response.BuscaItemPageResponse;
import br.com.cwi.crescer.api.controller.response.imagem.ImagemResponse;
import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.dto.BuscaItemDto;
import br.com.cwi.crescer.api.dto.BuscaItemDtoImpl;
import br.com.cwi.crescer.api.mapper.item.BuscaItemDtoMapper;
import br.com.cwi.crescer.api.repository.imagem.ImagemRepository;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscaItemServiceTest {

    @InjectMocks
    private BuscaItemService tested;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ImagemRepository imagemRepository;

    @Mock
    private BuscaItemDtoMapper mapper;

    @Test
    public void deveRetornarListaDeBuscaItemPageResponseComOsItensCorretamente() {
        int itensPorPagina = 20;
        int totalDePaginas = 1;
        int id = 1;
        BuscaItemRequest request = new BuscaItemRequest();
        request.setNome("PessoaTest");
        request.setPagePost(1);
        request.setRaio(20);
        request.setLatitude(-30.025134);
        request.setLongitude(-51.202820);

        Pageable pageable = PageRequest.of(request.getPagePost(), itensPorPagina);

        double latitude = (request.getLatitude() * Math.PI / 180);
        double longitude = (request.getLongitude() * Math.PI / 180);

        BuscaItemDtoImpl itemDto = new BuscaItemDtoImpl(id,"armario 1",BigDecimal.valueOf(20.0),"GG","cidade",-30.025134,-51.202820 );
        List<BuscaItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        Page<BuscaItemDto> itens = new PageImpl<>(itemDtos,pageable,totalDePaginas);
        List<Imagem> imagens = new ArrayList<>();
        imagens.add(new Imagem());

        BuscaItemResponse buscaItemResponse = new BuscaItemResponse(id,itemDto.getTitulo(),itemDto.getPreco(),itemDto.getTamanho(),itemDto.getNome(),new ArrayList<>(),1.5);
        List<BuscaItemResponse> response = new ArrayList<>();
        response.add(buscaItemResponse);


        Mockito.when(itemRepository.findByFilter(request.getNome(), request.getTamanho(), request.getTipo(),request.getCor(),
                request.getEstadoUso(), request.getPrecoIni(),request.getPreco(), request.getAceitaTroca(), latitude, longitude, request.getRaio(), pageable)).thenReturn(itens);

        Mockito.when(imagemRepository.findByIdItemIdItem(id)).thenReturn(imagens);

        Mockito.when(mapper.apply(itemDto,imagens,request.getLatitude(),request.getLongitude())).thenReturn(buscaItemResponse);

        BuscaItemPageResponse result = tested.find(request);


        Mockito.verify(itemRepository).findByFilter(request.getNome(), request.getTamanho(), request.getTipo(),request.getCor(),
                request.getEstadoUso(), request.getPrecoIni(),request.getPreco(), request.getAceitaTroca(), latitude, longitude, request.getRaio(), pageable);

        Mockito.verify(imagemRepository).findByIdItemIdItem(id);

        Mockito.verify(mapper).apply(itemDto,imagens,request.getLatitude(),request.getLongitude());

        Assert.assertEquals(itemDtos.size(),result.getItens().size());



    }


    @Test
    public void deveRetornarListaDeBuscaItemPageResponseComAQuantidadeDePaginas() {
        int itensPorPagina = 20;
        int id = 1;
        int totalDePaginas = 1;
        BuscaItemRequest request = new BuscaItemRequest();
        request.setNome("PessoaTest");
        request.setPagePost(1);
        request.setRaio(20);
        request.setLatitude(-30.025134);
        request.setLongitude(-51.202820);

        Pageable pageable = PageRequest.of(request.getPagePost(), itensPorPagina);

        double latitude = (request.getLatitude() * Math.PI / 180);
        double longitude = (request.getLongitude() * Math.PI / 180);

        BuscaItemDtoImpl itemDto = new BuscaItemDtoImpl(id,"armario 1",BigDecimal.valueOf(20.0),"GG","cidade",-30.025134,-51.202820 );
        List<BuscaItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        Page<BuscaItemDto> itens = new PageImpl<>(itemDtos,pageable,totalDePaginas);
        List<Imagem> imagens = new ArrayList<>();
        imagens.add(new Imagem());

        BuscaItemResponse buscaItemResponse = new BuscaItemResponse(id,itemDto.getTitulo(),itemDto.getPreco(),itemDto.getTamanho(),itemDto.getNome(),new ArrayList<>(),1.5);
        List<BuscaItemResponse> response = new ArrayList<>();
        response.add(buscaItemResponse);


        Mockito.when(itemRepository.findByFilter(request.getNome(), request.getTamanho(), request.getTipo(),request.getCor(),
                request.getEstadoUso(), request.getPrecoIni(),request.getPreco(), request.getAceitaTroca(), latitude, longitude, request.getRaio(), pageable)).thenReturn(itens);

        Mockito.when(imagemRepository.findByIdItemIdItem(id)).thenReturn(imagens);

        Mockito.when(mapper.apply(itemDto,imagens,request.getLatitude(),request.getLongitude())).thenReturn(buscaItemResponse);

        BuscaItemPageResponse result = tested.find(request);


        Mockito.verify(itemRepository).findByFilter(request.getNome(), request.getTamanho(), request.getTipo(),request.getCor(),
                request.getEstadoUso(), request.getPrecoIni(),request.getPreco(), request.getAceitaTroca(), latitude, longitude, request.getRaio(), pageable);

        Mockito.verify(imagemRepository).findByIdItemIdItem(id);

        Mockito.verify(mapper).apply(itemDto,imagens,request.getLatitude(),request.getLongitude());

        Assert.assertEquals(itens.getTotalPages(),result.getTotalPages());



    }


}
