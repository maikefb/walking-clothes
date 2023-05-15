package br.com.cwi.crescer.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notificacao")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNotificacao;

    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;

    private String mensagem;

    @Column(name = "ISATIVA")
    private boolean isativa;
}
