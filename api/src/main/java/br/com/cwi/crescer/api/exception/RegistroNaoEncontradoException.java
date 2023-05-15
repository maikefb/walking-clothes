package br.com.cwi.crescer.api.exception;

import org.springframework.http.HttpStatus;

public class RegistroNaoEncontradoException extends ErroAbstratoException {

    public RegistroNaoEncontradoException(String registro) {
        super(String.format("%s não encontrado(a).", registro), HttpStatus.NOT_FOUND);
    }
}