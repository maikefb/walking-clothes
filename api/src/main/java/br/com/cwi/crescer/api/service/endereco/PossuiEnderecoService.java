package br.com.cwi.crescer.api.service.endereco;

import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.endereco.EnderecoRepository;
import br.com.cwi.crescer.api.service.usuario.BuscarUsuarioPorEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PossuiEnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public boolean apply(String email) {
        Usuario usuario = buscarUsuarioPorEmailService.buscar(email);
        return repository.existsByIdEndereco(usuario.getIdUsuario());
    }
}
