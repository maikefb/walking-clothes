package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.comentario.ComentarRequest;
import br.com.cwi.crescer.api.service.comentario.CurtirComentarioService;
import br.com.cwi.crescer.api.service.comentario.ResponderComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ComentarioController {

    @Autowired
    private CurtirComentarioService curtirComentarioService;

    @Autowired
    private ResponderComentarioService responderComentarioService;

    @PostMapping("curtir/comentario/{idComentario}")
    @ResponseStatus(HttpStatus.OK)
    public void curtirComentario(@PathVariable Integer idComentario) {
        curtirComentarioService.curtir(idComentario);
    }

    @PostMapping("responder/comentario/{idComentario}")
    @ResponseStatus(HttpStatus.CREATED)
    public void responderComentario(@RequestBody ComentarRequest request, @PathVariable Integer idComentario) {
        responderComentarioService.responder(request, idComentario);
    }

}
