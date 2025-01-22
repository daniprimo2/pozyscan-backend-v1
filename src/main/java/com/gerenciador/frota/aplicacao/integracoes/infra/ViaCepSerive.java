package com.gerenciador.frota.aplicacao.integracoes.infra;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ViaCepSerive {

    private final RestTemplate restTemplate;

    public ViaCepSerive(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public EnderecoResponse buscarEnderecoPorCep(String cep) {
        log.info("[START] - Iniciando o consumo da api via cep, buscando o cep: {}", cep);
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        log.info("[INFO] - URL da busca {}", url);
        var reponse = restTemplate.getForObject(url, EnderecoResponse.class);
        log.info("[END] -  Via cep encontrou o  endereco {}", reponse);
        return reponse;
    }

    public Endereco buscarEnderecoPorCep(String cep, String numero, String complemento) {
        log.info("[START] - Iniciando o consumo da api via cep, buscando o cep: {}", cep);
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        log.info("[INFO] - URL da busca {}", url);
        EnderecoResponse enderecoRecuperado = restTemplate.getForObject(url, EnderecoResponse.class);
        log.info("[END] -  Via cep encontrou o  endereco {}", enderecoRecuperado);
        return enderecoRecuperado.construirEndereco(numero, complemento);
    }
}
