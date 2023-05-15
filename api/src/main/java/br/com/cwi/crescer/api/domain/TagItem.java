package br.com.cwi.crescer.api.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TagItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTagItem;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTag")
    private TagEstilo tagEstilo;

    @OneToMany
    @JoinColumn(name = "idItem")
    private List<Item> itens = new ArrayList<>();

    @JoinColumn(name = "idItem")
    @ManyToOne
    private Item idItem;

}
