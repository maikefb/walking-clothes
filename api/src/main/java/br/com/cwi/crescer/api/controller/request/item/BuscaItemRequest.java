package br.com.cwi.crescer.api.controller.request.item;


import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaItemRequest {

    private String nome;

    private Optional<String> tamanho;

    private Optional<String> tipo;

    private Optional<String> cor;

    private Optional<String> estadoUso;

    private Optional<BigDecimal> precoIni;

    private Optional<BigDecimal> preco;

    private Optional<Boolean> aceitaTroca;

    private double latitude;

    private double longitude;

    private Integer raio;

    private int pagePost;

}