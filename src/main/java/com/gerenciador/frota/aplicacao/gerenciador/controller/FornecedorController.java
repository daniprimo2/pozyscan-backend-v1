package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FornecedorFiltroRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FornecedorRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;


    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarFornecedor(@RequestBody FornecedorRequest fornecedorRequest) {
        if (fornecedorRequest.getNome().isEmpty())
            throw new RuntimeException("Nome do Forncedor deve ser preenchido");

        return ResponseEntity.ok(fornecedorService.adicionarFornecedor(fornecedorRequest));
    }

    @PostMapping("/buscar/filtro")
    public PageImpl<?> buscarFornecedorComFiltro(@RequestBody FornecedorFiltroRequest fornecedorFiltroRequest,
                                                 @RequestParam(value = "size", defaultValue = "10") int size,
                                                 @RequestParam(value = "page", defaultValue = "1") int page){
        Pageable pageable = PageRequest.of(page, size);
        return fornecedorService.buscarFiltroFornecedor(fornecedorFiltroRequest, pageable);
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarFornecedor(@PathVariable Long id){
        return fornecedorService.deletarFornecedorPorId(id);
    }
}
