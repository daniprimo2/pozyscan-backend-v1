package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.ContatoRquest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.ContatoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.FornecedorRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Object adicionarNovoContato(ContatoRquest contatoRquest) {

        if (!fornecedorRepository.existsById(Long.valueOf(contatoRquest.getIdDoFornecedor()))){
            return RetornoServicoBase.negativo("O Fornecedor informado não existe.");
        }

        Contato contato = contatoRquest.construindoContato(fornecedorRepository);

        return contatoRepository.save(contato);
    }
}
