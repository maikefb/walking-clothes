package br.com.cwi.crescer.api.service.armario;

import br.com.cwi.crescer.api.controller.request.armario.EditarArmarioRequest;
import br.com.cwi.crescer.api.domain.Armario;
import br.com.cwi.crescer.api.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.api.repository.armario.ArmarioRepository;
import br.com.cwi.crescer.api.service.tagestilo.BuscarTagEstiloPorNomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EditarArmarioServiceTest {

    @InjectMocks
    private EditarArmarioService tested;

    @Mock
    private BuscarArmarioPorIdService buscarArmarioPorIdService;

    @Mock
    private BuscarTagEstiloPorNomeService buscarTagEstiloPorNomeService;

    @Mock
    private ArmarioRepository repository;

    @Test
    public void deveEditarInformacoesDoArmarioDoIdInformado() {
        EditarArmarioRequest request = new EditarArmarioRequest();
        int id = 0;
        Armario armario = new Armario();

        Mockito.when(buscarArmarioPorIdService.buscar(id)).thenReturn(armario);
        tested.apply(request, id);

        Mockito.verify(buscarArmarioPorIdService).buscar(id);
        Mockito.verify(buscarTagEstiloPorNomeService).apply(request.getTagDeEstilo());
        Mockito.verify(repository).save(armario);
    }

    @Test
    public void deveCriarTagDeEstiloEEditarInformacoesDoArmarioDoIdInformado() {
        EditarArmarioRequest request = new EditarArmarioRequest();
        int id = 0;
        Armario armario = new Armario();

        Mockito.when(buscarArmarioPorIdService.buscar(id)).thenReturn(armario);
        Mockito.when(buscarTagEstiloPorNomeService.apply(request.getTagDeEstilo())).thenThrow(RegistroNaoEncontradoException.class);
        tested.apply(request, id);

        Mockito.verify(buscarArmarioPorIdService).buscar(id);
        Mockito.verify(buscarTagEstiloPorNomeService).apply(request.getTagDeEstilo());
        Mockito.verify(repository).save(armario);
    }

}
