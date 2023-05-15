package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarAosFavoritosService {
    @Autowired
    private BuscarItemPorIdService buscarItemPorIdService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    public void add(Integer id) {
        Item item = buscarItemPorIdService.find(id);
        Usuario usuario = usuarioLogadoService.get();
        if(usuario.getFavoritos().contains(item)){
            usuario.getFavoritos().remove(item);
        }else {
            usuario.getFavoritos().add(item);
        }

        usuarioRepository.save(usuario);
    }
}
