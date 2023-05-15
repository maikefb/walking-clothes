package br.com.cwi.crescer.api.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMunicipio;

    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "NOME")
    private String nome;

    @JoinColumn(name = "id_uf")
    @ManyToOne
    private Estado estado;

}
