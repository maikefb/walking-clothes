package br.com.cwi.crescer.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMensagem;

    @ManyToOne
    @JoinColumn(name = "id_remetente")
    private Usuario remetente;

    @Column(name = "mensagem")
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "id_destinatario")
    private Usuario destinatario;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_chat")
    private Chat chat;
}
