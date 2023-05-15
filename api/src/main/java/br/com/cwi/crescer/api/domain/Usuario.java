package br.com.cwi.crescer.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "email")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FOTO_PERFIL")
    private String fotoPerfil;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @JoinColumn(name = "id_endereco")
    @ManyToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Armario> armarios = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "favorito", joinColumns =
    @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idItem"))
    private List<Item> favoritos = new ArrayList<>();

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes = new ArrayList<>();

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
    private List<Pedido> vendas = new ArrayList<>();

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

}
