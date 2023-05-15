package br.com.cwi.crescer.api.service.usuario;

import br.com.cwi.crescer.api.controller.request.usuario.LoginRequest;
import br.com.cwi.crescer.api.security.TokenProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@RunWith(MockitoJUnitRunner.class)
public class FazerLoginServiceTest {

    @InjectMocks
    private FazerLoginService tested;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenProvider tokenProvider;

    @Test
    public void deveRealizarLogin() {
        LoginRequest request = new LoginRequest();
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Mockito.when(authenticationManager.authenticate(authentication)).thenReturn(authentication);
        tested.login(request);

        Mockito.verify(authenticationManager).authenticate(authentication);
        Mockito.verify(tokenProvider).createToken(authentication);
    }

}
