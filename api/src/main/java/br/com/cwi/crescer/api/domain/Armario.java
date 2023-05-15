package br.com.cwi.crescer.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Armario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArmario;

    @Column(name = "nome")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tag")
    private TagEstilo tagDeEstilo;

    @OneToMany(mappedBy = "idArmario")
    private List<Item> itens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_Usuario")
    private Usuario usuario;

}
