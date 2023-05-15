package br.com.cwi.crescer.api.controller.request.armario;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscaArmarioRequest {

    private double latitude;

    private double longitude;

    private int raio;

    private int pagePost;

}
