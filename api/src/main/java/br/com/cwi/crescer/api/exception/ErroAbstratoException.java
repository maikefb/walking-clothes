package br.com.cwi.crescer.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ErroAbstratoException extends RuntimeException {

    private final String mensagem;
    private final HttpStatus status;

    public ErroAbstratoException(String mensagem, HttpStatus status) {
        super(mensagem);
        this.mensagem = mensagem;
        this.status = status;
    }

}
