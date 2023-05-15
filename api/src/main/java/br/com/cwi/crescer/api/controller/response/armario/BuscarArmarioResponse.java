package br.com.cwi.crescer.api.controller.response.armario;

import br.com.cwi.crescer.api.controller.response.item.ItemArmarioResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuscarArmarioResponse {
    private Integer idArmario;
    private String nome;
    private String tagDeEstilo;
    private List<ItemArmarioResponse> itens;
    private String nomeVendedor;
    private String cidadeVendedor;
    private double distanciaVendedorUsuario;
    private Integer qtdPecasDisponiveis;
}
