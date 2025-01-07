package com.gerenciador.frota.aplicacao.integracoes.service;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.integracoes.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private ViaCepSerive viaCepSerive;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoResponse  buscarEnderecoViaCep(String cep) {
        EnderecoResponse enderecoResponse = viaCepSerive.buscarEnderecoPorCep(cep);
        return enderecoResponse;
    }

}
