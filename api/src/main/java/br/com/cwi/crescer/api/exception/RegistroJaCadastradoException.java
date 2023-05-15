package br.com.cwi.crescer.api.exception;

import org.springframework.http.HttpStatus;


public class RegistroJaCadastradoException extends ErroAbstratoException  {
    public RegistroJaCadastradoException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}


