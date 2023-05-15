package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.mapper.armario.BuscarArmarioResponseMapper;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExibirPaginaDoArmarioService {

    @Autowired
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    @Autowired
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    @Autowired
    private BuscarArmarioResponseMapper buscarArmarioResponseMapper;

    public BuscarArmarioResponse apply(Integer idArmario) {
        Armario armario = buscarArmarioPorIdService.buscar(idArmario);
        List<ItemArmarioResponse> itens = armario.getItens()
                .stream()
                .filter(i -> i.getStatus().equals(Status.D))
                .map(i -> itemArmarioResponseMapper.apply(i))
                .collect(Collectors.toList());
        return buscarArmarioResponseMapper.apply(armario, itens);
    }
}
