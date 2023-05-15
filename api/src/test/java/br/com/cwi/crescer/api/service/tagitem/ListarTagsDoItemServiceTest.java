package br.com.cwi.crescer.api.service.tagitem;

import br.com.cwi.crescer.api.domain.TagEstilo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ListarTagsDoItemServiceTest {

    @InjectMocks
    private ListarTagsDoItemService tested;

    @Test
    public void deveListarTagsDoItem() {
        List<TagEstilo> tags = new ArrayList<>();

        List<String> listaDeTags = tested.apply(tags);

        Assert.assertNotNull(listaDeTags);
    }
}
