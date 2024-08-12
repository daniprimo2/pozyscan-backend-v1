package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.gerenciador.dto.request.TipoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TipoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;

    public Tipo adicionarTipo (TipoRequest tipoRequest){
        return tipoRepository.save(tipoRequest.construirTipo());
    }

    public List<Tipo> buscarTodosOsTipos() {
        return tipoRepository.findAll();
    }
}
