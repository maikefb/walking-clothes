package br.com.cwi.crescer.api.controller.request.item;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CadastrarItemRequest {

    @NotEmpty
    private String titulo;
    private String descricao;

    @NotEmpty
    private String tamanho;
    private double busto;
    private double comprimento;
    private double quadril;

    @NotEmpty
    private String tipo;

    @NotEmpty
    private String cor;
    private String marca;

    @NotEmpty
    private String estadoUso;

    @NotNull
    private double preco;

    private double precoOriginal;

    @NotNull
    private boolean aceitaTroca;

    @NotNull
    private Integer idArmario;

    @NotEmpty
    private List<String> tags;

    @NotEmpty
    private List<String> imgUrl;
}
