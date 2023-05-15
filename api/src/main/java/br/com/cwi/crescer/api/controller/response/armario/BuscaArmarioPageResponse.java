package br.com.cwi.crescer.api.controller.response.armario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaArmarioPageResponse {

    private List<BuscaArmarioDtoResponse> itens;
    private int currentPage;
    private int totalPages;

}
