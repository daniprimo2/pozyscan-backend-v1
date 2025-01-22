package com.gerenciador.frota.aplicacao.integracoes.controller;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

@Disabled
class IntegracoesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
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
        when(enderecoService.buscarEnderecoViaCep("12345678")).thenReturn(enderecoResponse);

        mockMvc.perform(get("/api/integracoes/endereco")
                        .param("cep", "12345678")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value("12345678"))
                .andExpect(jsonPath("$.logradouro").value("Rua Teste"))
                .andExpect(jsonPath("$.bairro").value("Bairro Teste"))
                .andExpect(jsonPath("$.cidade").value("Cidade Teste"))
                .andExpect(jsonPath("$.uf").value("UF"))
                .andExpect(jsonPath("$.estado").value("Estado Teste"))
                .andExpect(jsonPath("$.complemento").value("Apt 202"));

        verify(enderecoService, times(1)).buscarEnderecoViaCep("12345678");
    }

    @Test
    void deveRetornarErro400QuandoCepNaoForInformado() throws Exception {
        mockMvc.perform(get("/api/integracoes/endereco")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarErro404QuandoCepInvalido() throws Exception {
        when(enderecoService.buscarEnderecoViaCep("00000000"))
                .thenThrow(new IllegalArgumentException("CEP inválido"));

        mockMvc.perform(get("/api/integracoes/endereco")
                        .param("cep", "00000000")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(enderecoService, times(1)).buscarEnderecoViaCep("00000000");
    }
}
