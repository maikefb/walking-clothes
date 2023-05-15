package br.com.cwi.crescer.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaItemDtoImpl implements BuscaItemDto {

    private Integer idItem;

    private String titulo;

    private BigDecimal preco;

    private String tamanho;

    private String nome;

    private double latitude;

    private double longitude;

}
