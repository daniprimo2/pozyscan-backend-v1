package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.AplicacaoFiltroRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.AplicacaoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.AplicacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aplicacao")
public class AplicacaoController {

    @Autowired
    private AplicacaoService aplicacaoService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarNovaAplicacao(@RequestBody AplicacaoRequest aplicacaoRequest) {
        if (aplicacaoRequest.getTipo().isEmpty())
            throw new RuntimeException("Campo tipo da applicacao deve ser preenchido.");
        return ResponseEntity.ok(aplicacaoService.salvarNovaAplicacao(aplicacaoRequest));
    }

    @PostMapping("/buscar/Filtro")
    public PageImpl<?> buscarAplicacaoComFiltro(@RequestBody AplicacaoFiltroRequest aplicacaoFiltroRequest,
                                                @RequestParam(value = "size", defaultValue = "10") int size,
                                                @RequestParam(value = "page", defaultValue = "1") int page){
        System.out.println(aplicacaoFiltroRequest);
        Pageable pageable = PageRequest.of(page, size);
        return aplicacaoService.buscarAplicacoesComFiltro(aplicacaoFiltroRequest, pageable);
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarAplicacao(@PathVariable Long id) {
        return aplicacaoService.deletarAplicacaoPorId(id);
    }

}
