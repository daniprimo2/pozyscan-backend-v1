package com.gerenciador.frota.aplicacao.autenticacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/teste")
public class TesteController {

    @GetMapping
    public ResponseEntity<String> getTeste(){
        return ResponseEntity.ok("TESTE");
    }
}
