package br.com.cwi.crescer.api.mapper.armario;

import br.com.cwi.crescer.api.controller.response.armario.BuscaArmarioDtoResponse;
import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import br.com.cwi.crescer.api.dto.BuscaArmarioDto;
import br.com.cwi.crescer.api.service.armario.ContarItensAVendaArmarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaArmarioDtoMapper {

    @Autowired
    private ContarItensAVendaArmarioService contarItensAVendaArmarioService;

    public BuscaArmarioDtoResponse apply(BuscaArmarioDto armario, List<BuscaItemResponse> itens, double distancia){
        BuscaArmarioDtoResponse response = new BuscaArmarioDtoResponse();
        response.setNomeVendedor(armario.getNomeUsuario());
        response.setNome(armario.getNomeArmario());
        response.setCidadeVendedor(armario.getNomeCidade());
        response.setItens(itens);
        response.setTagDeEstilo(armario.getNomeTag());
        response.setIdArmario(armario.getIdArmario());
        response.setDistanciaVendedorUsuario(distancia);
        Integer qtdItensAVenda = contarItensAVendaArmarioService.apply(armario.getIdArmario());
        response.setQtdPecasDisponiveis(qtdItensAVenda);
        return response;
    }

}
