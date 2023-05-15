package br.com.cwi.crescer.api.mapper.item;

import br.com.cwi.crescer.api.controller.response.item.PaginaDoItemResponse;
import br.com.cwi.crescer.api.domain.Item;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.service.CalculaDistanciaService;
import br.com.cwi.crescer.api.service.imagem.ListarImagensItemService;
import br.com.cwi.crescer.api.service.item.VerificarItemFavoritoService;
import br.com.cwi.crescer.api.service.tagitem.ListarTagsDoItemService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaginaItemMapper {

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CalculaDistanciaService calculaDistanciaService;

    @Autowired
    private ListarImagensItemService listarImagensItemService;

    @Autowired
    private ListarTagsDoItemService listarTagsDoItemService;

    @Autowired
    private VerificarItemFavoritoService verificarItemFavoritoService;

    public PaginaDoItemResponse apply(Item item) {
        Usuario usuario = usuarioLogadoService.get();
        PaginaDoItemResponse paginaDoItemResponse = modelMapper.map(item, PaginaDoItemResponse.class);
        paginaDoItemResponse.setArmario(item.getIdArmario().getNome());
        paginaDoItemResponse.setIdVendedor(item.getIdArmario().getUsuario().getIdUsuario());
        paginaDoItemResponse.setVendedor(item.getIdArmario().getUsuario().getNome());
        paginaDoItemResponse.setCidade(item.getIdArmario().getUsuario().getEndereco().getCidade().getNome());
        paginaDoItemResponse.setEstado(item.getIdArmario().getUsuario().getEndereco().getCidade().getEstado().getNome());

        double distancia = calculaDistanciaService.apply(
                usuario.getEndereco().getLatitude().doubleValue(), usuario.getEndereco().getLongitude().doubleValue(),
                item.getIdArmario().getUsuario().getEndereco().getLatitude().doubleValue(), item.getIdArmario().getUsuario().getEndereco().getLongitude().doubleValue());

        paginaDoItemResponse.setDistancia(distancia);

        paginaDoItemResponse.setCor(item.getCor().name());
        paginaDoItemResponse.setEstadoUso(item.getEstadoUso().getNome());
        paginaDoItemResponse.setStatus(item.getStatus().getDescricao());
        paginaDoItemResponse.setTipo(item.getTipo().getNome());

        List<String> imagensItem = listarImagensItemService.apply(item.getImagens());
        paginaDoItemResponse.setImagens(imagensItem);

        List<String> tagsItem = listarTagsDoItemService.apply(item.getTagsEstilos());
        paginaDoItemResponse.setTagsEstilos(tagsItem);

        boolean isFavorito = verificarItemFavoritoService.apply(item);
        paginaDoItemResponse.setFavorito(isFavorito);

        paginaDoItemResponse.setItemDoUsuarioLogado(item.getIdArmario().getUsuario().getIdUsuario().equals(usuario.getIdUsuario()));

        return paginaDoItemResponse;
    }
}
