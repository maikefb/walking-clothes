package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.CadastroUsuarioRequest;
import br.com.cwi.crescer.api.domain.Usuario;
import br.com.cwi.crescer.api.mapper.usuario.CadastroUsuarioMapper;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.validator.CadastroUsuarioRequestValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarUsuarioServiceTest {

    @InjectMocks
    private CadastrarUsuarioService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CadastroUsuarioMapper cadastroUsuarioMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private CadastroUsuarioRequestValidator validator;

    @Test
    public void deveCadastrarUsuario() {
        CadastroUsuarioRequest cadastroUsuarioRequest = new CadastroUsuarioRequest();
        Usuario usuario = new Usuario();
        usuario.setArmarios(new ArrayList<>());

        Mockito.when(cadastroUsuarioMapper.apply(cadastroUsuarioRequest)).thenReturn(usuario);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        tested.register(cadastroUsuarioRequest);

        Mockito.verify(validator).accept(cadastroUsuarioRequest);
        Mockito.verify(cadastroUsuarioMapper).apply(cadastroUsuarioRequest);
        Mockito.verify(passwordEncoder).encode(usuario.getPassword());
        Mockito.verify(usuarioRepository).save(usuario);
        Assert.assertEquals(cadastroUsuarioRequest.getNome(), usuario.getNome());
        Assert.assertEquals(cadastroUsuarioRequest.getEmail(), usuario.getEmail());
        Assert.assertEquals(cadastroUsuarioRequest.getDataNascimento(), usuario.getDataNascimento());
        Assert.assertEquals(cadastroUsuarioRequest.getPassword(), usuario.getPassword());
    }

}
