package br.com.cwi.crescer.api.controller.response.usuario;

import br.com.cwi.crescer.api.controller.response.armario.BuscarArmarioResponse;
import br.com.cwi.crescer.api.domain.Municipio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilUsuarioResponse {
    private String nome;
    private String fotoPerfil;
    private Municipio cidade;
    private Integer qtdItensVendidos;
    private Integer qtdItensAVenda;
    private List<BuscarArmarioResponse> armarios;

}
