package br.com.cwi.crescer.api.service.comentario;

import br.com.cwi.crescer.api.controller.request.comentario.ComentarRequest;
import br.com.cwi.crescer.api.domain.*;
import br.com.cwi.crescer.api.repository.comentario.ComentarioRepository;
import br.com.cwi.crescer.api.repository.usuario.UsuarioRepository;
import br.com.cwi.crescer.api.service.item.BuscarItemPorIdService;
import br.com.cwi.crescer.api.service.usuario.UsuarioLogadoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ComentarItemServiceTest {

    @InjectMocks
    private ComentarItemService tested;

    @Mock
    private BuscarItemPorIdService buscarItemPorIdService;

    @Mock
    private UsuarioLogadoService usuarioLogadoService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    public void deveRealizarComentarioNoItemInformadoECriarNotificacao() {
        ComentarRequest comentarRequest = new ComentarRequest();
        int id = 1;
        Item item = new Item();
        Armario armario = new Armario();
        item.setIdArmario(armario);
        Usuario usuario = new Usuario();
        armario.setUsuario(usuario);
        List<Notificacao> notificacoes = new ArrayList<>();
        usuario.setNotificacoes(notificacoes);

        Mockito.when(buscarItemPorIdService.find(id)).thenReturn(item);
        Mockito.when(usuarioLogadoService.get()).thenReturn(usuario);
        Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
        tested.comentar(comentarRequest, id);

        Mockito.verify(buscarItemPorIdService).find(id);
        Mockito.verify(usuarioLogadoService).get();
        Mockito.verify(usuarioRepository).save(usuario);
    }

}
