package br.com.cwi.crescer.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "endereco")
@Entity
@Setter
@Getter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;

    @Column(name = "LATITUDE")
    private BigDecimal latitude;

    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    @JoinColumn(name = "ID_MUNICIPIO")
    @ManyToOne
    private Municipio cidade;

    private String rua;

    private Integer numero;

    private String complemento;

}
