package br.com.cwi.crescer.api.mapper.armario;

import br.com.cwi.crescer.api.controller.response.armario.OpcoesArmarioResponse;
import br.com.cwi.crescer.api.domain.Armario;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class OpcoesArmarioResponseMapperTest {

    @InjectMocks
    private OpcoesArmarioResponseMapper tested;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveMapearOOpcoesArmarioResponse() {
        Armario armario = new Armario();
        OpcoesArmarioResponse opcoesArmarioResponse = new OpcoesArmarioResponse();

        Mockito.when(modelMapper.map(armario, OpcoesArmarioResponse.class)).thenReturn(opcoesArmarioResponse);
        OpcoesArmarioResponse result = tested.apply(armario);

        Mockito.verify(modelMapper).map(armario, OpcoesArmarioResponse.class);
        Assert.assertEquals(armario.getNome(), result.getNome());
        Assert.assertEquals(armario.getIdArmario(), result.getIdArmario());
    }
}
