package br.com.cwi.crescer.api.mapper.armario;

import br.com.cwi.crescer.api.controller.response.armario.OpcoesArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpcoesArmarioResponseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public OpcoesArmarioResponse apply(Armario armario) {
        return modelMapper.map(armario, OpcoesArmarioResponse.class);
    }

}
