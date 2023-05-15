package br.com.cwi.crescer.api.mapper.usuario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.controller.response.usuario.PerfilUsuarioResponse;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.service.armario.BuscarArmariosUsuarioService;
import br.com.cwi.crescer.api.service.usuario.ContarItensAVendaUsuarioService;
import br.com.cwi.crescer.api.service.usuario.ContarItensVendidosPeloUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PerfilUsuarioResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContarItensVendidosPeloUsuarioService contarItensVendidosPeloUsuarioService;

    @Autowired
    private ContarItensAVendaUsuarioService contarItensAVendaUsuarioService;

    @Autowired
    private BuscarArmariosUsuarioService buscarArmariosUsuarioService;

    public PerfilUsuarioResponse apply(Usuario usuario, Integer pagePost) {
        PerfilUsuarioResponse response = modelMapper.map(usuario, PerfilUsuarioResponse.class);

        Integer qtdItensVendidos = contarItensVendidosPeloUsuarioService.apply(usuario.getIdUsuario());
        response.setQtdItensVendidos(qtdItensVendidos);

        Integer qtdItensAVenda = contarItensAVendaUsuarioService.apply(usuario.getIdUsuario());
        response.setQtdItensAVenda(qtdItensAVenda);

        List<BuscarArmarioResponse> armarios = buscarArmariosUsuarioService.buscar(usuario.getIdUsuario(), pagePost);
        response.setArmarios(armarios);

        return response;
    }

}
