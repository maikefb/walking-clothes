package br.com.cwi.crescer.api.validator;

import br.com.cwi.crescer.api.controller.request.usuario.CadastroUsuarioRequest;
import br.com.cwi.crescer.api.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CadastroUsuarioRequestValidatorTest {

    @InjectMocks
    private CadastroUsuarioRequestValidator cadastroUsuarioRequestValidator;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveValidarOCadastroUsuarioRequest() {
        CadastroUsuarioRequest request = new CadastroUsuarioRequest();

        cadastroUsuarioRequestValidator.accept(request);

        Mockito.verify(usuarioRepository).existsByEmail(request.getEmail());
    }

    @Test(expected = ValidacaoNegocioException.class)
    public void deveLancarExcecaoQuandoJaExistirUmUsuarioComOEmailInformadoNoRequest() {
        CadastroUsuarioRequest request = new CadastroUsuarioRequest();

        Mockito.when(usuarioRepository.existsByEmail(request.getEmail())).thenReturn(true);
        cadastroUsuarioRequestValidator.accept(request);

        Mockito.verify(usuarioRepository).existsByEmail(request.getEmail());
    }
}
