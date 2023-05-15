package br.com.cwi.crescer.api.controller.response.item;

import br.com.cwi.crescer.api.controller.response.imagem.ImagemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaItemResponse {

    private Integer idItem;
    private String titulo;
    private BigDecimal preco;
    private String tamanho;
    private String cidadeVendedor;
    private List<ImagemResponse> imagens;
    private double distancia;

}
