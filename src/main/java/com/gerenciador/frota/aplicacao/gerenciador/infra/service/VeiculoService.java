package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.VeiculoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.CategoriaRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TipoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.VeiculoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TipoRepository tipoRepository;

    public Veiculo adicionarVeiculo(VeiculoRequest veiculoRequest) {
        return veiculoRepository.save(veiculoRequest.construirVeiculo(veiculoRequest,
                categoriaRepository,
                tipoRepository));
    }



}
