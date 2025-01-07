package com.gerenciador.frota.aplicacao.integracoes.infra;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepSerive {

    private final RestTemplate restTemplate;

    public ViaCepSerive(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, EnderecoResponse.class);
    }

    public Endereco buscarEnderecoPorCep(String cep, String numero, String complemento) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        EnderecoResponse enderecoRecuperado = restTemplate.getForObject(url, EnderecoResponse.class);
        return enderecoRecuperado.construirEndereco(numero, complemento);
    }
}
