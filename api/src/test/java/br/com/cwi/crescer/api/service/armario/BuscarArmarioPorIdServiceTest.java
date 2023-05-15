package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarArmarioPorIdServiceTest {

    @InjectMocks
    private BuscarArmarioPorIdService tested;

    @Mock
    private ArmarioRepository armarioRepository;

    @Test
    public void deveRetornarArmarioEncontrado() {
        int id = 0;
        Armario armario = new Armario();
        armario.setNome("Armario de teste");
        Optional<Armario> armarioOptional = Optional.of(armario);

        Mockito.when(armarioRepository.findByIdArmario(id)).thenReturn(armarioOptional);
        Armario result = tested.buscar(id);

        Mockito.verify(armarioRepository).findByIdArmario(id);
        Assert.assertEquals(armario.getNome(), result.getNome());
    }

    @Test(expected = RegistroNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoArmarioNaoForEncontrado() {
        int id = 0;

        tested.buscar(id);

        Mockito.verify(armarioRepository).findByIdArmario(id);
    }

}
