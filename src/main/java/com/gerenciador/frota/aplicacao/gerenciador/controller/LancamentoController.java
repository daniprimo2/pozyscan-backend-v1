package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.LancamentoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectsResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;

    @PostMapping("/novo")
    public ResponseEntity<RetornoServicoBase> registrarNovoLancamento(@RequestBody LancamentoRequest lancamentoRequest){
        return ResponseEntity.ok(lancamentoService.registrarNovoLancamento(lancamentoRequest));
    }

    @GetMapping("/listaDosSelects")
    public ResponseEntity<SelectsResponse> buscarOsSelects(){
        return ResponseEntity.ok(lancamentoService.buscarSelects());
    }

}
