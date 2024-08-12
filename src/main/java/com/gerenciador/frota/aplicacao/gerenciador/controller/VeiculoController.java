package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.VeiculoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.CategoriaRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TipoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.VeiculoService;
import com.gerenciador.frota.aplicacao.gerenciador.model.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarVeiculo(@RequestBody VeiculoRequest veiculoRequest) {
        return ResponseEntity.ok(veiculoService.adicionarVeiculo(veiculoRequest));
    }

}
