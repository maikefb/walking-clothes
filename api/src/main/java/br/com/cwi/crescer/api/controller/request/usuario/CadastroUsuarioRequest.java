package br.com.cwi.crescer.api.controller.request.usuario;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class CadastroUsuarioRequest {

    @NotEmpty
    @NotBlank
    private String nome;

    @Email
    @NotEmpty
    @NotBlank
    private String email;

    @NotEmpty
    @NotBlank
    private String password;

    @NotNull
    private LocalDate dataNascimento;

}
