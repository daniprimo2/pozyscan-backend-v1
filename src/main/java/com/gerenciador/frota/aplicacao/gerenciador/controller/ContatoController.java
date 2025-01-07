package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.ContatoRquest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.ContatoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Gestão de Financeiro", description = "Controladores de gestao para para o Gerenciadores financeiros")
@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    public ResponseEntity<?> adicionarUmContato(@RequestBody ContatoRquest contatoRquest) {
        if (contatoRquest.getNomeContato().isEmpty())
            throw new RuntimeException("Nome do contado deve ser preenchido");
        return ResponseEntity.ok(contatoService.adicionarNovoContato(contatoRquest));
    }


}
