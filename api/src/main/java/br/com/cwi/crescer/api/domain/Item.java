package br.com.cwi.crescer.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    private String titulo;
    private String descricao;
    private String tamanho;

    @Column(name = "busto", columnDefinition = "number(3,2)")
    private BigDecimal busto;

    @Column(name = "comprimento", columnDefinition = "number(3,2)")
    private BigDecimal comprimento;

    @Column(name = "quadril", columnDefinition = "number(3,2)")
    private BigDecimal quadril;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    private Cor cor;

    @Enumerated(EnumType.STRING)
    private EstadoUso estadoUso;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String marca;

    @Column(name = "precoOriginal", columnDefinition = "number(38,2)")
    private BigDecimal precoOriginal;

    @Column(name = "preco", columnDefinition = "number(38,2)")
    private BigDecimal preco;
    private boolean aceitaTroca;

    @OneToMany(mappedBy = "idItem", cascade = CascadeType.ALL)
    private List<Imagem> imagens  = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tag_item", joinColumns =
    @JoinColumn(name = "idItem")
            ,inverseJoinColumns = @JoinColumn(name = "idTag"))
    private List<TagEstilo> tagsEstilos = new ArrayList<>();

    @OneToMany(mappedBy = "idItem", cascade = CascadeType.ALL)
    private List<Comentario> comentarios  = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idArmario")
    private Armario idArmario;

}
