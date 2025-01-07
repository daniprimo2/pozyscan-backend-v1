package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FiltroVeiculoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.VeiculoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.CategoriaRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TipoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.VeiculoService;
import com.gerenciador.frota.aplicacao.gerenciador.model.Tipo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Gestão de Financeiro", description = "Controladores de gestao para para o Gerenciadores financeiros")
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarVeiculo(@RequestBody VeiculoRequest veiculoRequest) {
        if (veiculoRequest.getPlaca().isEmpty())
            throw new RuntimeException("Placa é campo obrigatorio");

        return ResponseEntity.ok(veiculoService.adicionarVeiculo(veiculoRequest));
    }

    @PostMapping("/busca/filtro")
    public PageImpl<?> buscarVeiculosComFiltro(@RequestBody FiltroVeiculoRequest filtroVeiculoRequest,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "page", defaultValue = "1") int page){

        Pageable pageable = PageRequest.of(page, size);
        return veiculoService.buscarVeiculoComFiltro(filtroVeiculoRequest, pageable);
    }

    @DeleteMapping("/deletar/{placa}")
    public RetornoServicoBase deletarVeiculosPorPlaca(@PathVariable String placa) {
        return veiculoService.deletarVeiculoPorPLaca(placa);
    }


}
