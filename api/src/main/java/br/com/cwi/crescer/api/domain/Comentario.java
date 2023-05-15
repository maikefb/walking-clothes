package br.com.cwi.crescer.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @JsonIgnore
    @JoinColumn(name = "id_usuario")
    @ManyToOne(cascade = CascadeType.ALL)
    private Usuario idUsuario;

    @Column(name = "MENSAGEM")
    private String mensagem;

    @JsonIgnore
    @JoinColumn(name = "ID_ITEM")
    @ManyToOne
    private Item idItem;

    @JsonIgnore
    @OneToMany(mappedBy = "comentario", cascade = CascadeType.ALL)
    private List<Curtida> curtidas;

    @JsonIgnore
    @JoinColumn(name = "id_comentario_base")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

}
