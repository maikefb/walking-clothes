package br.com.cwi.crescer.api.domain;

import lombok.Getter;

@Getter
public enum Tipo {

    BERMUDA("Bermuda"),
    BLAZER("Blazer"),
    BLUSA("Blusa"),
    BLUSAO("Blusão"),
    BONE("Boné"),
    CACHECOL("Cachecol"),
    CALCA("Calça"),
    CALCADO("Calçado"),
    CAMISA("Camisa"),
    CAMISETA("Camiseta"),
    CASACO("Camiseta"),
    CHAPEU("Chapéu"),
    JAQUETA("Jaqueta"),
    MACACAO("Macacão"),
    MEIA("Meia"),
    MOLETOM("Moletom"),
    SAIA("Saia"),
    SHORT("Short"),
    SUETER("Suéter"),
    TOUCA("Touca"),
    VESTIDO("Vestido");

    private final String nome;

    Tipo(String nome) {
        this.nome = nome;
    }
}
