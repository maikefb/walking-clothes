package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.request.armario.BuscaArmarioRequest;
import br.com.cwi.crescer.api.controller.response.armario.BuscaArmarioDtoResponse;
import br.com.cwi.crescer.api.controller.response.armario.BuscaArmarioPageResponse;
import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.dto.BuscaArmarioDto;
import br.com.cwi.crescer.api.mapper.armario.BuscaArmarioDtoMapper;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.CalculaDistanciaService;
import br.com.cwi.crescer.api.service.item.BuscarItensPorIdArmarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuscaArmariosService {
    private static final int QUANTIDADE_ARMARIO_POR_PAGINA = 10;

    @Autowired
    private ArmarioRepository armarioRepository;

    @Autowired
    private BuscaArmarioDtoMapper mapper;

    @Autowired
    private BuscarItensPorIdArmarioService buscarItens;

    @Autowired
    private CalculaDistanciaService calculaDistancia;

    public BuscaArmarioPageResponse find(BuscaArmarioRequest request) {

        Pageable pageable = PageRequest.of(request.getPagePost(), QUANTIDADE_ARMARIO_POR_PAGINA);

        double latitude = request.getLatitude() * Math.PI / 180;
        double longitude = request.getLongitude() * Math.PI / 180;
        List<BuscaArmarioDtoResponse> response = new ArrayList<>();
        Page<BuscaArmarioDto> armarios = armarioRepository.findAllByFilter(latitude, longitude, request.getRaio(), pageable);

        for (BuscaArmarioDto armario : armarios) {
            List<BuscaItemResponse> itens = buscarItens.buscar(armario.getIdArmario());
            double distancia = calculaDistancia.apply(request.getLatitude(), request.getLongitude(), armario.getLatitude(), armario.getLongitude());
            response.add(mapper.apply(armario, itens, distancia));
        }
        return new BuscaArmarioPageResponse(response, armarios.getNumber(), armarios.getTotalPages());
    }

}
