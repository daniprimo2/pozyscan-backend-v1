package com.gerenciador.frota.aplicacao.rh.dominio.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class EnderecoNaoFoiSlavoException extends RuntimeException {

    private final String codigoErro = "ENDERECO_NAO_FOI_SALVO";


    public EnderecoNaoFoiSlavoException(String message) {
        super(message);
    }
}
