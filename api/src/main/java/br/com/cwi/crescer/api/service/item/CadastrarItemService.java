package br.com.cwi.crescer.api.service.item;

import br.com.cwi.crescer.api.controller.request.item.CadastrarItemRequest;
import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.mapper.item.CadastrarItemRequestMapper;
import br.com.cwi.crescer.api.repository.item.ItemRepository;
import br.com.cwi.crescer.api.service.armario.BuscarArmarioPorIdService;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CadastrarItemRequestMapper cadastrarItemRequestMapper;

    @Autowired
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Autowired
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    @Autowired
    private MapearImagensDoItemService mapearImagensDoItemService;

    public void cadastrar(CadastrarItemRequest request) {
        Item item = cadastrarItemRequestMapper.apply(request);

        TagEstilo tagEstilo;

        try {
            tagEstilo = buscarTagEstiloPorNomeService.apply(request.getTags().get(0));
        } catch (RegistroNaoEncontradoException exception) {
            tagEstilo = new TagEstilo();
            tagEstilo.setNome(request.getTags().get(0));
        }

        item.getTagsEstilos().add(tagEstilo);

        List<Imagem> imagensDoItem = mapearImagensDoItemService.apply(request.getImgUrl(), item);
        item.setImagens(imagensDoItem);

        Armario armario = buscarArmarioPorIdService.buscar(request.getIdArmario());
        item.setStatus(Status.D);
        item.setIdArmario(armario);
        itemRepository.save(item);
    }
}
