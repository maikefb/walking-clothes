package br.com.cwi.crescer.api.controller.request.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
