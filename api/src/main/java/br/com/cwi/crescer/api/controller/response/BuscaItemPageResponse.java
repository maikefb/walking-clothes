package br.com.cwi.crescer.api.controller.response;

import br.com.cwi.crescer.api.controller.response.item.BuscaItemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BuscaItemPageResponse {

    private List<BuscaItemResponse> itens;
    private int currentPage;
    private int totalPages;
}
