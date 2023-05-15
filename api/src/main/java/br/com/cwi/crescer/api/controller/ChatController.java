package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.MensagemRequest;
import br.com.cwi.crescer.api.controller.response.chat.ChatResponse;
import br.com.cwi.crescer.api.service.chat.BuscarChatService;
import br.com.cwi.crescer.api.service.chat.EnviarMensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private EnviarMensagemService enviarMensagemService;
    @Autowired
    private BuscarChatService buscarChatPorIdService;

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public ChatResponse send(@Payload MensagemRequest mensagemRequest){
        enviarMensagemService.send(mensagemRequest);
    return buscarChatPorIdService.find(mensagemRequest.getIdChat());
}
}
