package br.com.cwi.crescer.api.dto;

import java.math.BigDecimal;

public interface BuscaItemDto {


    Integer getIdItem();

    String getTitulo();

    BigDecimal getPreco();

    String getTamanho();

    String getNome();

    double getLatitude();

    double getLongitude();

}