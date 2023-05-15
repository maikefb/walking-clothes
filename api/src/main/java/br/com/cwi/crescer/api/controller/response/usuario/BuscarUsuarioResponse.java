package br.com.cwi.crescer.api.controller.response.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuscarUsuarioResponse {
    private Integer idUsuario;
    private String nome;
    private String fotoPerfil;
}
