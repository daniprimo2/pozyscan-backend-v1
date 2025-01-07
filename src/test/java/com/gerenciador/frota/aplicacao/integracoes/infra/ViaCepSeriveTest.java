package com.gerenciador.frota.aplicacao.integracoes.infra;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViaCepSeriveTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ViaCepSerive viaCepSerive;

    @Test
    void deveBuscarEnderecoPorCepComSucesso() {
        // Configuração
        String cep = "12345678";
        EnderecoResponse enderecoResponse = new EnderecoResponse();
        enderecoResponse.setLogradouro("Rua Teste");
        enderecoResponse.setBairro("Bairro Teste");
        enderecoResponse.setEstado("Cidade Teste");
        enderecoResponse.setUf("UF");

        // Mocking do RestTemplate para simular a resposta da API
        when(restTemplate.getForObject("https://viacep.com.br/ws/12345678/json/", EnderecoResponse.class))
                .thenReturn(enderecoResponse);

        // Ação
        EnderecoResponse response = viaCepSerive.buscarEnderecoPorCep(cep);

        // Verificação
        assertNotNull(response);
        assertEquals("Rua Teste", response.getLogradouro());
        assertEquals("Bairro Teste", response.getBairro());
        assertEquals("Cidade Teste", response.getEstado());
        assertEquals("UF", response.getUf());

        verify(restTemplate, times(1)).getForObject("https://viacep.com.br/ws/12345678/json/", EnderecoResponse.class);
    }

    @Test
    void deveRetornarEnderecoComNumeroEComplemento() {
        // Configuração
        String cep = "12345678";
        String numero = "100";
        String complemento = "Apt 202";
        EnderecoResponse enderecoResponse = new EnderecoResponse();
        enderecoResponse.setLogradouro("Rua Teste");
        enderecoResponse.setBairro("Bairro Teste");
        enderecoResponse.setEstado("Cidade Teste");
        enderecoResponse.setUf("UF");

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setEstado("Cidade Teste");
        endereco.setUf("UF");
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);

        // Mocking do RestTemplate
        when(restTemplate.getForObject("https://viacep.com.br/ws/12345678/json/", EnderecoResponse.class))
                .thenReturn(enderecoResponse);

        // Ação
        Endereco enderecoComNumeroComplemento = viaCepSerive.buscarEnderecoPorCep(cep, numero, complemento);

        // Verificação
        assertNotNull(enderecoComNumeroComplemento);
        assertEquals("Rua Teste", enderecoComNumeroComplemento.getLogradouro());
        assertEquals("Bairro Teste", enderecoComNumeroComplemento.getBairro());
        assertEquals("Cidade Teste", enderecoComNumeroComplemento.getEstado());
        assertEquals("UF", enderecoComNumeroComplemento.getUf());
        assertEquals("100", enderecoComNumeroComplemento.getNumero());

        verify(restTemplate, times(1)).getForObject("https://viacep.com.br/ws/12345678/json/", EnderecoResponse.class);
    }

    @Test
    void deveRetornarNullQuandoCepInvalido() {
        // Configuração
        String cep = "00000000";

        // Mocking do RestTemplate para simular a resposta de erro (cep inválido ou não encontrado)
        when(restTemplate.getForObject("https://viacep.com.br/ws/00000000/json/", EnderecoResponse.class))
                .thenReturn(null);

        // Ação
        EnderecoResponse response = viaCepSerive.buscarEnderecoPorCep(cep);

        // Verificação
        assertNull(response);
        verify(restTemplate, times(1)).getForObject("https://viacep.com.br/ws/00000000/json/", EnderecoResponse.class);
    }

}
