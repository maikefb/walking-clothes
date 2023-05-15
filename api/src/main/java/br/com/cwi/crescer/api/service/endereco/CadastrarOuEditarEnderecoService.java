package br.com.cwi.crescer.api.service.endereco;

import br.com.cwi.crescer.api.controller.request.endereco.EnderecoRequest;
import br.com.cwi.crescer.api.domain.Endereco;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.endereco.EnderecoRepository;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.localizacao.ObterInformacoesLocalizacaoService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarOuEditarEnderecoService {

    @Autowired
    private ObterInformacoesLocalizacaoService obterInformacoesLocalizacaoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioLogadoService usuarioLogadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void apply(EnderecoRequest request) {
        Endereco endereco = obterInformacoesLocalizacaoService.apply(request.getCep());

        endereco.setNumero(request.getNumero());

        if (request.getComplemento() != null && !request.getComplemento().isEmpty() && !request.getComplemento().trim().isEmpty()) {
            endereco.setComplemento(request.getComplemento());
        }

        enderecoRepository.save(endereco);

        Usuario usuario = usuarioLogadoService.get();
        usuario.setEndereco(endereco);
        usuarioRepository.save(usuario);
    }

}
