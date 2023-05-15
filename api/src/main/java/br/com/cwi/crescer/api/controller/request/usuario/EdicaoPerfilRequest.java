package br.com.cwi.crescer.api.controller.request.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EdicaoPerfilRequest {
    private String nome;
    private String email;
    private String fotoPerfil;
    private String descricao;
}
