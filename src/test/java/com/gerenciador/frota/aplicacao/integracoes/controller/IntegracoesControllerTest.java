package com.gerenciador.frota.aplicacao.integracoes.controller;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegracoesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    private EnderecoResponse enderecoResponse;

    @BeforeEach
    void setUp() {
        enderecoResponse = new EnderecoResponse(
                "12345678", "Rua Teste", "Bairro Teste", "Cidade Teste", "UF", "Estado Teste", "Apt 202"
        );
    }

    @Test
    void deveRetornarEnderecoComStatus200() throws Exception {
        // Configura o mock para o serviço
        when(enderecoService.buscarEnderecoViaCep("12345678")).thenReturn(enderecoResponse);

        // Realiza a requisição GET e verifica se o retorno é o esperado
        mockMvc.perform(get("/api/integracoes/endereco")
                        .param("cep", "12345678")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("12345678"))
                .andExpect(jsonPath("$.logradouro").value("Rua Teste"))
                .andExpect(jsonPath("$.bairro").value("Bairro Teste"))
                .andExpect(jsonPath("$.localidade").value("Cidade Teste"))
                .andExpect(jsonPath("$.uf").value("UF"))
                .andExpect(jsonPath("$.estado").value("Estado Teste"))
                .andExpect(jsonPath("$.complemento").value("Apt 202"));

        // Verifica se o método do serviço foi chamado corretamente
        verify(enderecoService, times(1)).buscarEnderecoViaCep("12345678");
    }

    @Test
    void deveRetornarErro400QuandoCepNaoForInformado() throws Exception {
        // Realiza a requisição GET sem o parâmetro cep
        mockMvc.perform(get("/api/integracoes/endereco")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarEnderecoComStatus200EComplemento() throws Exception {
        // Configura o mock para o serviço com complemento
        EnderecoResponse enderecoComComplemento = new EnderecoResponse(
                "12345678", "Rua Teste", "Bairro Teste", "Cidade Teste", "UF", "Estado Teste", "Apt 202"
        );
        when(enderecoService.buscarEnderecoViaCep("12345678")).thenReturn(enderecoComComplemento);

        // Realiza a requisição GET e verifica se o retorno é o esperado
        mockMvc.perform(get("/api/integracoes/endereco")
                        .param("cep", "12345678")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("12345678"))
                .andExpect(jsonPath("$.logradouro").value("Rua Teste"))
                .andExpect(jsonPath("$.bairro").value("Bairro Teste"))
                .andExpect(jsonPath("$.localidade").value("Cidade Teste"))
                .andExpect(jsonPath("$.uf").value("UF"))
                .andExpect(jsonPath("$.estado").value("Estado Teste"))
                .andExpect(jsonPath("$.complemento").value("Apt 202"));
    }

    @Test
    @Disabled
    void deveRetornarBadRequestQuandoCepInvalido() throws Exception {
        // Configura o mock para um erro no serviço
        when(enderecoService.buscarEnderecoViaCep("123")).thenThrow(new IllegalArgumentException("CEP inválido"));

        // Realiza a requisição GET e verifica se o retorno é o esperado
        mockMvc.perform(get("/api/integracoes/endereco")
                        .param("cep", "123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }
}
