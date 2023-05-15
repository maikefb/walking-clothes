package br.com.cwi.crescer.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuscaArmarioDtoImpl implements BuscaArmarioDto {

    private Integer idArmario;

    private String nomeArmario;

    private String nomeTag;

    private String nomeUsuario;

    private String nomeCidade;

    private double latitude;

    private double longitude;

}