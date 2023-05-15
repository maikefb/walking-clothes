package br.com.cwi.crescer.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImagem;

    @ManyToOne
    @JoinColumn(name = "idItem")
    private Item idItem;

    @Column(name = "urlImagem")
    private String urlImagem;

    public Imagem(String urlImagem, Item idItem) {
        this.urlImagem = urlImagem;
        this.idItem = idItem;
    }

}
