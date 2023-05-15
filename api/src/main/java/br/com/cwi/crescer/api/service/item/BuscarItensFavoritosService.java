package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.item.ItemArmarioResponseMapper;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarItensFavoritosService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private ItemArmarioResponseMapper itemArmarioResponseMapper;

    public List<ItemArmarioResponse> find() {
        Usuario usuario = usuarioLogadoService.get();
        return usuario.getFavoritos().stream().map(item -> itemArmarioResponseMapper.apply(item)).collect(Collectors.toList());
    }
}
