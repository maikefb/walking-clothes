package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContarItensAVendaUsuarioService {

    @Autowired
    private ItemRepository repository;

    public Integer apply(Integer idUsuario) {
        return repository.quantidadeItensAVendaUsuario(idUsuario);
    }

}
