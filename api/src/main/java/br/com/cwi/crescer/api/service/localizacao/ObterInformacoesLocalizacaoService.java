package br.com.cwi.crescer.api.service.localizacao;

import br.com.cwi.crescer.api.domain.Endereco;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.mapper.endereco.EnderecoMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class ObterInformacoesLocalizacaoService {

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private RestTemplate restTemplate;

    public Endereco apply(String enderecoRequest) {

        String results = "results";
        String addressComponents = "address_components";

        StringBuilder url = new StringBuilder();
        url
                .append("https://maps.googleapis.com/maps/api/geocode/json?address=")
                .append(enderecoRequest)
                .append("&key=AIzaSyBb9d-W0joD1_he3tNBtdti4dWQAWLH5FY");

        String response =
                restTemplate.getForObject(url.toString(), String.class);

        JSONObject obj = new JSONObject(response);

        if (obj.getJSONArray("results").isEmpty()) {
            throw new RegistroNaoEncontradoException("Endereço");
        }

        String rua = obj
                .getJSONArray(results)
                .getJSONObject(0)
                .getJSONArray(addressComponents)
                .getJSONObject(1)
                .getString("long_name");

        String cidade = obj
                .getJSONArray(results)
                .getJSONObject(0)
                .getJSONArray(addressComponents)
                .getJSONObject(3)
                .getString("long_name");

        BigDecimal latitude = obj
                .getJSONArray(results)
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location")
                .getBigDecimal("lat");

        BigDecimal longitude = obj
                .getJSONArray(results)
                .getJSONObject(0)
                .getJSONObject("geometry")
                .getJSONObject("location")
                .getBigDecimal("lng");

        return enderecoMapper.apply(rua, cidade, latitude, longitude);
    }
}
