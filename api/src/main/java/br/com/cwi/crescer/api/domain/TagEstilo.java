package br.com.cwi.crescer.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
public class TagEstilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTag;

    @Column(name = "nome")
    private String nome;

}
