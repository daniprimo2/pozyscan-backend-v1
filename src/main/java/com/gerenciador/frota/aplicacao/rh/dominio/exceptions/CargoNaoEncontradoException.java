package com.gerenciador.frota.aplicacao.rh.dominio.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CargoNaoEncontradoException extends RuntimeException {

    private final String codigoErro = "CARGO_NAO_ENCONTRADO";

    public CargoNaoEncontradoException (String mensagem) {
        super(mensagem);
    }



}
