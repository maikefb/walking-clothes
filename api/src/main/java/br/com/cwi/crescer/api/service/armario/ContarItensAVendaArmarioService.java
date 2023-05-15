package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContarItensAVendaArmarioService {

    @Autowired
    private ItemRepository itemRepository;

    public Integer apply(Integer idArmario) {
        return itemRepository.quantidadeItensAVendaArmario(idArmario);
    }

}
