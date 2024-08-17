package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.TipoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.TipoService;
import com.gerenciador.frota.aplicacao.gerenciador.model.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo")
public class TipoController {
    @Autowired
    private TipoService tipoService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarTipo(@RequestBody TipoRequest tipoRequest) {
        if (tipoRequest.getNome().isEmpty())
            throw new RuntimeException("Nome do tipo deve ser preenchido");
        return ResponseEntity.ok(tipoService.adicionarTipo(tipoRequest));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Tipo>> buscarTipoDeVeiculo() {
        return ResponseEntity.ok(tipoService.buscarTodosOsTipos());
    }



}
