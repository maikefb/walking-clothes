package br.com.cwi.crescer.api.controller.response.item;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class PaginaDoItemResponse {
    private Integer idItem;
    private String armario;
    private String vendedor;
    private Integer idVendedor;
    private String cidade;
    private String estado;
    private boolean isFavorito;
    private String titulo;
    private String descricao;
    private String tamanho;
    private BigDecimal busto;
    private BigDecimal comprimento;
    private BigDecimal quadril;
    private String tipo;
    private String cor;
    private String estadoUso;
    private String status;
    private String marca;
    private BigDecimal precoOriginal;
    private BigDecimal preco;
    private boolean aceitaTroca;
    private List<String> imagens;
    private List<String> tagsEstilos;
    private boolean isItemDoUsuarioLogado;
    private double distancia;
}
