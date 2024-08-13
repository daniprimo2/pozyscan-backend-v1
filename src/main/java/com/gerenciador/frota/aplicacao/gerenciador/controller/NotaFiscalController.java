package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.NotaFiscalRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notaFiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;


    @PostMapping("/gerarNota")
    public ResponseEntity<?> gerarInsercaoNotaFiscal(@RequestBody NotaFiscalRequest notaFiscalRequest) {
        return ResponseEntity.ok(notaFiscalService.gerarIsercoesDasNotasFiscais(notaFiscalRequest));
    }

}
