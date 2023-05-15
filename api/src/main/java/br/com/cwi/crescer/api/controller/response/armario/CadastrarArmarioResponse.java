package br.com.cwi.crescer.api.controller.response.armario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarArmarioResponse {
    private Integer idArmario;
    private String nome;

    public CadastrarArmarioResponse(Integer id,String title) {
        this.idArmario = id;
        this.nome = title;
    }
}
