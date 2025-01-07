package com.gerenciador.frota.aplicacao.rh.dominio.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnderecoBadRquestException extends RuntimeException {

    public EnderecoBadRquestException(String message) {
        super(message);
    }
}
