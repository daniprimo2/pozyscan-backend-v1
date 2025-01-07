package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.ConcluirPagamentoDTO;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.LancamentoRelatorioRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.LancamentoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.ParcelaRelatorioRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.LancamentoRelatorioResponse;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.ParcelaInfoResponse;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.ParcelaRelatorioResponse;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectsResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.LancamentoService;
import com.gerenciador.frota.aplicacao.gerenciador.model.Parcela;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Gestão de Financeiro", description = "Controladores de gestao para para o Gerenciadores financeiros")
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

    @PostMapping("/buscaLancamentos/comFiltro")
    public ResponseEntity<List<LancamentoRelatorioResponse>> buscarLancamentos(@RequestBody LancamentoRelatorioRequest lancamentoRelatorioRequest) {
        return ResponseEntity.ok(lancamentoService.relatorioLancamentos(lancamentoRelatorioRequest));
    }

    @PostMapping("/buscaParcelas/comFiltro")
    public ResponseEntity<List<ParcelaRelatorioResponse>> buscarParcelas(@RequestBody ParcelaRelatorioRequest parcelaRelatorioRequest) {
        return ResponseEntity.ok(lancamentoService.relatorioParcelas(parcelaRelatorioRequest));
    }

    @DeleteMapping("/deletarLancamentos/{idLancamento}")
    public ResponseEntity<RetornoServicoBase> deletarLancamentoPorId(@PathVariable Long idLancamento) {
        return ResponseEntity.ok(lancamentoService.deletarLancamento(idLancamento));
    }

    @PostMapping("/registrarPagamento")
    public ResponseEntity<RetornoServicoBase> registrarPagamentoParcela(@RequestParam Long id, @RequestParam String notaFiscal) {
        return ResponseEntity.ok(lancamentoService.registrarPagamentoParcela(id, notaFiscal));
    }
    @PostMapping("/TESTE")
    public ResponseEntity<List<ParcelaInfoResponse>> registrarPagamentoParcela(@RequestParam String notaFiscais) {
        System.out.println("Entrou");
        return ResponseEntity.ok(lancamentoService.testeParcela(notaFiscais));
    }

}
