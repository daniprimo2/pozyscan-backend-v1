package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FornecedorRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;


    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarFornecedor(@RequestBody FornecedorRequest fornecedorRequest) {
        return ResponseEntity.ok(fornecedorService.adicionarFornecedor(fornecedorRequest));
    }
}
