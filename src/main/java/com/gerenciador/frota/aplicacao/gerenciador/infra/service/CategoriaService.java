package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.CategoriaRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.CategoriaRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria adicionarCategoria(CategoriaRequest categoriaRequest) {
        return categoriaRepository.save(categoriaRequest.constuirCategoria());
    }

    public List<Categoria> buscarTodasCategorias() {
        return categoriaRepository.findAll();
    }
}
