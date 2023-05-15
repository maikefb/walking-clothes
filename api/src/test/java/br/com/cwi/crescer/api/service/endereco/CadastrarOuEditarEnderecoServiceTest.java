package br.com.cwi.crescer.api.service.endereco;

import br.com.cwi.crescer.api.controller.request.endereco.EnderecoRequest;
import br.com.cwi.crescer.api.domain.Endereco;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.repository.endereco.EnderecoRepository;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.localizacao.ObterInformacoesLocalizacaoService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarOuEditarEnderecoServiceTest {

    @InjectMocks
    private CadastrarOuEditarEnderecoService tested;

    @Mock
    private ObterInformacoesLocalizacaoService obterInformacoesLocalizacaoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveCadastrarOuEditarOEndereco() {
        EnderecoRequest request = new EnderecoRequest();
        request.setComplemento("A");
        Endereco endereco = new Endereco();
        Usuario usuario = new Usuario();

        Mockito.when(obterInformacoesLocalizacaoService.apply(request.getCep())).thenReturn(endereco);
        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        tested.apply(request);

        Mockito.verify(obterInformacoesLocalizacaoService).apply(request.getCep());
        Mockito.verify(enderecoRepository).save(endereco);
        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(usuarioRepository).save(usuario);
    }

}
