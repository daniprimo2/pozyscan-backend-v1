package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.NotaFiscalRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.NotaFiscalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gestão de Financeiro", description = "Controladores de gestao para para o Gerenciadores financeiros")
@RestController
@RequestMapping("/notaFiscal")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping("/gerarNota")
    public ResponseEntity<?> gerarInsercaoNotaFiscal(@RequestBody NotaFiscalRequest notaFiscalRequest) {
        if (notaFiscalRequest.getNumeroNotaFiscal().isEmpty())
            throw new RuntimeException("Numero da nota deve ser informado");
        return ResponseEntity.ok(notaFiscalService.gerarIsercoesDasNotasFiscais(notaFiscalRequest));
    }
    @GetMapping("/buscarNota/{numero}")
    public ResponseEntity<?> buscarNotaFiscalPorNumero(@PathVariable String numero) {
        return ResponseEntity.ok(notaFiscalService.buscarInformacoesFiscaisPorNumero(numero));
    }

}
