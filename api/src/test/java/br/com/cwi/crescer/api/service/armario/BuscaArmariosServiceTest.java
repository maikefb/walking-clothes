package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.request.armario.BuscaArmarioRequest;
import br.com.cwi.crescer.api.controller.response.armario.BuscaArmarioDtoResponse;
import br.com.cwi.crescer.api.controller.response.armario.BuscaArmarioPageResponse;
import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Imagem;
import br.com.cwi.crescer.api.dto.BuscaArmarioDto;
import br.com.cwi.crescer.api.dto.BuscaArmarioDtoImpl;
import br.com.cwi.crescer.api.mapper.armario.BuscaArmarioDtoMapper;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.CalculaDistanciaService;
import br.com.cwi.crescer.api.service.item.BuscarItensPorIdArmarioService;
import org.aspectj.lang.annotation.Aspect;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscaArmariosServiceTest {

    @InjectMocks
    private BuscaArmariosService tested;

    @Mock
    private ArmarioRepository armarioRepository;

    @Mock
    private BuscarItensPorIdArmarioService buscarItens;

    @Mock
    private CalculaDistanciaService calculaDistancia;

    @Mock
    private BuscaArmarioDtoMapper mapper;

    @Test
    public void deveRetornarONumeroTotalDePaginasDoArmario() {
        int armarioPorPagina = 10;
        int totalDePaginas = 1;
        int id = 1;

        BuscaArmarioRequest request = new BuscaArmarioRequest();
        request.setPagePost(1);
        request.setRaio(20);
        request.setLatitude(-30.025134);
        request.setLongitude(-51.202820);
        Pageable pageable = PageRequest.of(request.getPagePost(), armarioPorPagina);

        double latitude = request.getLatitude() * Math.PI / 180;
        double longitude = request.getLongitude() * Math.PI / 180;

        BuscaArmarioDtoImpl armarioDto = new BuscaArmarioDtoImpl(id,"NomeArmario","Tag1","Usuario1","Cidade1",-30.035134,-51.232820);
        List<BuscaArmarioDto> armarioDtos = new ArrayList<>();
        armarioDtos.add(armarioDto);
        Page<BuscaArmarioDto>armarios = new PageImpl<>(armarioDtos,pageable,totalDePaginas);

        double distancia = 1.50;


        List<BuscaArmarioDtoResponse> response = new ArrayList<>();

        BuscaItemResponse itemResponse = new BuscaItemResponse();
        itemResponse.setDistancia(0.0);
        itemResponse.setCidadeVendedor(armarioDto.getNomeCidade());
        itemResponse.setTamanho("GG");
        itemResponse.setPreco(BigDecimal.valueOf(25.90));
        itemResponse.setTitulo(armarioDto.getNomeArmario());
        itemResponse.setIdItem(id);

        List<BuscaItemResponse> itens = new ArrayList<>();
        itens.add(itemResponse);

        BuscaArmarioDtoResponse armarioDtoResponse = new BuscaArmarioDtoResponse(armarioDto.getIdArmario(),armarioDto.getNomeArmario(),
                armarioDto.getNomeTag(), itens, armarioDto.getNomeUsuario(),armarioDto.getNomeCidade(),distancia,itens.size());
        response.add(armarioDtoResponse);

        Mockito.when(armarioRepository.findAllByFilter(latitude,longitude,request.getRaio(),pageable)).thenReturn(armarios);

        Mockito.when(buscarItens.buscar(armarioDto.getIdArmario())).thenReturn(itens);

        Mockito.when(calculaDistancia.apply(request.getLatitude(), request.getLongitude(), armarioDto.getLatitude(), armarioDto.getLongitude())).thenReturn(distancia);

        Mockito.when(mapper.apply(armarioDto,itens,distancia)).thenReturn(armarioDtoResponse);

        BuscaArmarioPageResponse result = tested.find(request);


        Mockito.verify(armarioRepository).findAllByFilter(latitude,longitude,request.getRaio(),pageable);
        Mockito.verify(buscarItens).buscar(armarioDto.getIdArmario());
        Mockito.verify(calculaDistancia).apply(request.getLatitude(), request.getLongitude(), armarioDto.getLatitude(), armarioDto.getLongitude());
        Mockito.verify(mapper).apply(armarioDto,itens,distancia);

        Assert.assertEquals(armarios.getTotalPages(),result.getTotalPages());




    }

    @Test
    public void deveRetornarUmaListaDeArmarios() {
        int armarioPorPagina = 10;
        int totalDePaginas = 1;
        int id = 1;

        BuscaArmarioRequest request = new BuscaArmarioRequest();
        request.setPagePost(1);
        request.setRaio(20);
        request.setLatitude(-30.025134);
        request.setLongitude(-51.202820);
        Pageable pageable = PageRequest.of(request.getPagePost(), armarioPorPagina);

        double latitude = request.getLatitude() * Math.PI / 180;
        double longitude = request.getLongitude() * Math.PI / 180;

        BuscaArmarioDtoImpl armarioDto = new BuscaArmarioDtoImpl(id,"NomeArmario","Tag1","Usuario1","Cidade1",-30.035134,-51.232820);
        List<BuscaArmarioDto> armarioDtos = new ArrayList<>();
        armarioDtos.add(armarioDto);
        Page<BuscaArmarioDto>armarios = new PageImpl<>(armarioDtos,pageable,totalDePaginas);

        double distancia = 1.50;

        List<BuscaArmarioDtoResponse> response = new ArrayList<>();

        BuscaItemResponse itemResponse = new BuscaItemResponse();
        itemResponse.setDistancia(0.0);
        itemResponse.setCidadeVendedor(armarioDto.getNomeCidade());
        itemResponse.setTamanho("GG");
        itemResponse.setPreco(BigDecimal.valueOf(25.90));
        itemResponse.setTitulo(armarioDto.getNomeArmario());
        itemResponse.setIdItem(id);

        List<BuscaItemResponse> itens = new ArrayList<>();
        itens.add(itemResponse);

        BuscaArmarioDtoResponse armarioDtoResponse = new BuscaArmarioDtoResponse(armarioDto.getIdArmario(),armarioDto.getNomeArmario(),
                armarioDto.getNomeTag(), itens, armarioDto.getNomeUsuario(),armarioDto.getNomeCidade(),distancia,itens.size());
        response.add(armarioDtoResponse);

        Mockito.when(armarioRepository.findAllByFilter(latitude,longitude,request.getRaio(),pageable)).thenReturn(armarios);

        Mockito.when(buscarItens.buscar(armarioDto.getIdArmario())).thenReturn(itens);

        Mockito.when(calculaDistancia.apply(request.getLatitude(), request.getLongitude(), armarioDto.getLatitude(), armarioDto.getLongitude())).thenReturn(distancia);

        Mockito.when(mapper.apply(armarioDto,itens,distancia)).thenReturn(armarioDtoResponse);

        BuscaArmarioPageResponse result = tested.find(request);


        Mockito.verify(armarioRepository).findAllByFilter(latitude,longitude,request.getRaio(),pageable);
        Mockito.verify(buscarItens).buscar(armarioDto.getIdArmario());
        Mockito.verify(calculaDistancia).apply(request.getLatitude(), request.getLongitude(), armarioDto.getLatitude(), armarioDto.getLongitude());
        Mockito.verify(mapper).apply(armarioDto,itens,distancia);

        Assert.assertEquals(armarioDtos.size(),result.getItens().size());

    }




}
