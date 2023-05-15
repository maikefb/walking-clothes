package br.com.cwi.crescer.api.mapper.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.service.CalculaDistanciaService;
import br.com.cwi.crescer.api.service.armario.ContarItensAVendaArmarioService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarArmarioResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContarItensAVendaArmarioService contarItensAVendaArmarioService;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private CalculaDistanciaService calculaDistanciaService;

    public BuscarArmarioResponse apply(Armario armario, List<ItemArmarioResponse> itens) {
        BuscarArmarioResponse buscarArmarioResponse = modelMapper.map(armario, BuscarArmarioResponse.class);

        buscarArmarioResponse.setTagDeEstilo(armario.getTagDeEstilo().getNome());

        buscarArmarioResponse.setItens(itens);

        buscarArmarioResponse.setNomeVendedor(armario.getUsuario().getNome());



        Usuario comprador = usuarioLogadoService.get();
        Usuario vendedor = armario.getUsuario();

        double distanciaVendedorComprador = calculaDistanciaService.apply(
                comprador.getEndereco().getLatitude().doubleValue(), comprador.getEndereco().getLongitude().doubleValue(),
                vendedor.getEndereco().getLatitude().doubleValue(), vendedor.getEndereco().getLongitude().doubleValue());

        buscarArmarioResponse.setDistanciaVendedorUsuario(distanciaVendedorComprador);

        Integer qtdItensAVenda = contarItensAVendaArmarioService.apply(armario.getIdArmario());

        buscarArmarioResponse.setQtdPecasDisponiveis(qtdItensAVenda);

        return buscarArmarioResponse;
    }
}
