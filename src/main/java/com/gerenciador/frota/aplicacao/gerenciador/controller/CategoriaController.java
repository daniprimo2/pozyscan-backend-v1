package com.gerenciador.frota.aplicacao.gerenciador.controller;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.CategoriaRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.service.CategoriaService;
import com.gerenciador.frota.aplicacao.gerenciador.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/adicionar")
    public ResponseEntity<Categoria> adicionarCategoria(@RequestBody CategoriaRequest categoriaRequest){
        return ResponseEntity.ok(categoriaService.adicionarCategoria(categoriaRequest));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity buscarTodasCategorias() {

            List<Categoria> categorias = categoriaService.buscarTodasCategorias();
            return ResponseEntity.ok(categorias);
    }

}
