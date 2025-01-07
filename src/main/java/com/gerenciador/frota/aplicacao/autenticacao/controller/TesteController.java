package com.gerenciador.frota.aplicacao.autenticacao.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Gestão de Acesso", description = "Controladores de de cadastro de usuarios e autenticação")
@RestController
@RequestMapping(name = "/teste")
public class TesteController {

    @GetMapping
    public ResponseEntity<String> getTeste(){
        return ResponseEntity.ok("TESTE");
    }
}
