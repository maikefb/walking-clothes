package br.com.cwi.crescer.api.controller.request.armario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CadastrarArmarioRequest {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String tagEstilo;
}
