package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.ContatoRquest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    public ResponseEntity<?> adicionarUmContato(@RequestBody ContatoRquest contatoRquest) {
        return ResponseEntity.ok(contatoService.adicionarNovoContato(contatoRquest));
    }


}
