package br.com.cwi.crescer.api.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @Column(name = "CODIGO_UF")
    private Integer codigoUf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "UF")
    private String uf;
}
