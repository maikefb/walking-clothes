package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.mapper.armario.BuscarArmarioResponseMapper;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarArmariosUsuarioService {

    @Autowired
    private ArmarioRepository repository;

    @Autowired
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    @Autowired
    private BuscarArmarioResponseMapper buscarArmarioResponseMapper;

    public List<BuscarArmarioResponse> buscar(Integer idUsuario, Integer pagePost) {
        int armarioPorPagina = 5;
        Pageable pageable = PageRequest.of(pagePost, armarioPorPagina);

        return repository.findByUsuarioIdUsuario(idUsuario, pageable)
                .stream()
                .map(a -> {
                    List<ItemArmarioResponse> itens =
                            a.getItens()
                                    .stream()
                                    .map(i -> itemArmarioResponseMapper.apply(i))
                                    .collect(Collectors.toList());

                    return buscarArmarioResponseMapper.apply(a, itens);
                })
                .collect(Collectors.toList());
    }

}
