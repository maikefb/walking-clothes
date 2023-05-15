package br.com.cwi.crescer.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_item", joinColumns =
    @JoinColumn(name = "idPedido")
    ,inverseJoinColumns = @JoinColumn(name = "idItem"))
    private List<Item> itens  = new ArrayList<>();


    @JoinColumn(name = "id_pedido")
    @OneToOne(cascade = CascadeType.ALL)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(name = "id_comprador")
    private Usuario comprador;

    private LocalDate data;
    private boolean isAtivo;
}
