package br.com.cwi.crescer.api.mapper.endereco;

import br.com.cwi.crescer.api.domain.Endereco;
import br.com.cwi.crescer.api.domain.Municipio;
import br.com.cwi.crescer.api.service.municipio.BuscarMunicipioPorNomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EnderecoMapper {

    @Autowired
    private BuscarMunicipioPorNomeService buscarMunicipioPorNomeService;

    public Endereco apply(String rua, String cidade, BigDecimal latitude, BigDecimal longitude) {
        Endereco endereco = new Endereco();
        endereco.setLatitude(latitude);
        endereco.setLongitude(longitude);
        endereco.setRua(rua);
        Municipio municipio = buscarMunicipioPorNomeService.apply(cidade);
        endereco.setCidade(municipio);
        return endereco;
    }

}
