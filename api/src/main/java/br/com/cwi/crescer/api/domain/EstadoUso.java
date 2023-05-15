package br.com.cwi.crescer.api.domain;

import lombok.Getter;

@Getter
public enum EstadoUso {
    N("Novo"),
    S("Seminovo"),
    U("Usado");

    private final String nome;

    EstadoUso(String nome) {
        this.nome = nome;
    }

}
