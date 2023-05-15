package br.com.cwi.crescer.api.controller.request.endereco;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoRequest {

    @NotEmpty
    @NotBlank
    private String cep;

    @NotNull
    private Integer numero;

    private String complemento;

}
