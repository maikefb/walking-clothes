package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.domain.Status;
import br.com.cwi.crescer.api.mapper.item.BuscarItemMapper;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarItensPorIdArmarioService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private BuscarItemMapper buscarItemMapper;

    public List<BuscaItemResponse> buscar(Integer idArmario) {
        return repository.findByIdArmarioIdArmarioAndStatus(idArmario, Status.D)
                .stream()
                .map(i -> buscarItemMapper.apply(i))
                .collect(Collectors.toList());
    }

}
