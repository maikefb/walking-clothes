package br.com.cwi.crescer.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "curtida")
public class Curtida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurtida;

    @JoinColumn(name = "idComentario")
    @ManyToOne
    private Comentario comentario;

    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private Usuario usuario;
}
