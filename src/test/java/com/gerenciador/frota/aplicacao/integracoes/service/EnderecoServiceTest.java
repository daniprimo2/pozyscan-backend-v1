package com.gerenciador.frota.aplicacao.integracoes.service;

import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.repository.EnderecoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private ViaCepSerive viaCepSerive;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void deveBuscarEnderecoViaCepComSucesso() {
        // Configuração
        String cep = "12345678";
        EnderecoResponse enderecoResponse = new EnderecoResponse();
        enderecoResponse.setLogradouro("Rua Teste");
        enderecoResponse.setBairro("Bairro Teste");
        enderecoResponse.setEstado("Cidade Teste");
        enderecoResponse.setUf("UF");

        // Mocking do serviço ViaCepSerive
        when(viaCepSerive.buscarEnderecoPorCep(cep)).thenReturn(enderecoResponse);

        // Ação
        EnderecoResponse response = enderecoService.buscarEnderecoViaCep(cep);

        // Verificação
        assertNotNull(response);
        assertEquals("Rua Teste", response.getLogradouro());
        assertEquals("Bairro Teste", response.getBairro());
        assertEquals("Cidade Teste", response.getEstado());
        assertEquals("UF", response.getUf());

        verify(viaCepSerive, times(1)).buscarEnderecoPorCep(cep);
    }

    @Test
    void deveRetornarNullQuandoNaoEncontrarEndereco() {
        // Configuração
        String cep = "00000000";

        // Mocking do serviço ViaCepSerive para retornar null (não encontrado)
        when(viaCepSerive.buscarEnderecoPorCep(cep)).thenReturn(null);

        // Ação
        EnderecoResponse response = enderecoService.buscarEnderecoViaCep(cep);

        // Verificação
        assertNull(response);
        verify(viaCepSerive, times(1)).buscarEnderecoPorCep(cep);
    }
}
