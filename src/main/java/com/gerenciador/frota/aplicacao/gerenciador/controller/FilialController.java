package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FilialRquest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FiltroFilialRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.FilialResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filial")
public class FilialController {

    @Autowired
    private FilialService filialService;

    @PostMapping("/adicionar")
    public ResponseEntity<FilialResponse> adicionarNovaFilial(@RequestBody FilialRquest filialRquest) {
        return ResponseEntity.ok(filialService.adicionarNovaFilial(filialRquest));
    }

    @PostMapping("/buscar/filtro")
    public PageImpl<?> buscarUsuariosComFiltro(@RequestBody FiltroFilialRequest filtroFilialRequest,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return filialService.buscarFiliaisComFiltro(filtroFilialRequest, pageable);
    }

    @DeleteMapping("/deletar/{id}")
    public RetornoServicoBase deletarFilialPorId(@PathVariable Long id) {
        return filialService.deletarFilialPorId(id);
    }

}
