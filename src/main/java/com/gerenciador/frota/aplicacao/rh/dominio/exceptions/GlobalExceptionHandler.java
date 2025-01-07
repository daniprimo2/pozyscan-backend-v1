package com.gerenciador.frota.aplicacao.rh.dominio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CargoNaoEncontradoException.class)
    public ResponseEntity<ProblemDetail> handlerNotFound(CargoNaoEncontradoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setProperty("codigoErro", ex.getCodigoErro());
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(ColaboradorNaoFoiSalvoException.class)
    public ResponseEntity<ProblemDetail> handlerBadRequestColaborador(ColaboradorNaoFoiSalvoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("codigoErro", ex.getCodigoErro());
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(EnderecoNaoFoiSlavoException.class)
    public ResponseEntity<ProblemDetail> handlerBadRequestEndereco(EnderecoNaoFoiSlavoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setProperty("codigoErro", ex.getCodigoErro());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }


}
