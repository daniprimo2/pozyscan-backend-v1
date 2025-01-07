package com.gerenciador.frota.aplicacao.integracoes.controller;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.integracoes.service.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gestão de Integrações", description = "Controladores de consumo de API externas.")
@RestController
@RequestMapping("/api/integracoes")
public class IntegracoesController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/endereco")
    public ResponseEntity<EnderecoResponse> pegarEnderecoCompleto(@RequestParam String cep) {
        return ResponseEntity.ok(enderecoService.buscarEnderecoViaCep(cep));
    }

}
