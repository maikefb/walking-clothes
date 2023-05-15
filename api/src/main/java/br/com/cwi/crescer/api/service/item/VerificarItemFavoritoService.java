package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificarItemFavoritoService {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    public boolean apply(Item item) {
        Usuario usuario = usuarioLogadoService.get();
        return usuario.getFavoritos().contains(item);
    }

}
