package br.com.cwi.crescer.api.service.municipio;

import br.com.cwi.crescer.api.domain.Municipio;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.municipio.MunicipioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarMunicipioPorNomeServiceTest {

    @InjectMocks
    private BuscarMunicipioPorNomeService tested;

    @Mock
    private MunicipioRepository repository;

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoMunicipioNaoForEncontrado() {
        String nome = "";

        tested.apply(nome);

        Mockito.verify(repository).findByNome(nome);
    }

    @Test
    public void deveRetornarMunicipioQuandoForEncontrado() {
        String nome = "";
        Municipio municipio = new Municipio();

        Mockito.when(repository.findByNome(nome)).thenReturn(Optional.of(municipio));
        Municipio result = tested.apply(nome);

        Mockito.verify(repository).findByNome(nome);
        Assert.assertNotNull(result);
    }

}
