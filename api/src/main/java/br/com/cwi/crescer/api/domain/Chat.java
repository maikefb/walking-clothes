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
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idChat;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Mensagem> mensagens = new ArrayList<>();

    @JoinColumn(name = "id_pedido")
    @OneToOne
    private Pedido pedido;


}
