package br.com.cwi.crescer.api.domain;

import lombok.Getter;

@Getter
public enum Status {
    D("Disponível"),
    N("Em Negociação"),
    I("Indisponível");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }
}
