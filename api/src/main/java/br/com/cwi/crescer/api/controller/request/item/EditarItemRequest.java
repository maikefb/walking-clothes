package br.com.cwi.crescer.api.controller.request.item;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class EditarItemRequest {
    private String titulo;
    private String descricao;
    private String tamanho;
    private BigDecimal busto;
    private BigDecimal comprimento;
    private BigDecimal quadril;
    private String tipo;
    private String cor;
    private String estadoUso;
    private String marca;
    private BigDecimal precoOriginal;
    private BigDecimal preco;
    private boolean aceitaTroca;
    private Integer idNovoArmario;

}
