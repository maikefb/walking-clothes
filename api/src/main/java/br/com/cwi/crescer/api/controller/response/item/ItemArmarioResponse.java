package br.com.cwi.crescer.api.controller.response.item;

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
public class ItemArmarioResponse {
    private Integer idItem;
    private String titulo;
    private String tamanho;
    private BigDecimal preco;
    private List<String> imagens;
}
