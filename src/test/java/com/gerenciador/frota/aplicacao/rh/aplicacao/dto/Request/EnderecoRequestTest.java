package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnderecoRequestTest {

    @Test
    void deveConstruirEnderecoUsandoViaCepService() {
        // Mock do serviço ViaCepService
        ViaCepSerive viaCepSerive = mock(ViaCepSerive.class);

        // Configuração do ambiente de teste
        String cep = "12345-678";
        String numero = "10";
        String complemento = "Apt 101";

        Endereco enderecoMock = Endereco.builder()
                .cep(cep)
                .logradouro("Rua Teste")
                .bairro("Bairro Teste")
                .numero(numero)
                .localidade("Cidade Teste")
                .uf("SP")
                .estado("São Paulo")
                .complemento(complemento)
                .build();

        when(viaCepSerive.buscarEnderecoPorCep(cep, numero, complemento)).thenReturn(enderecoMock);

        EnderecoRequest enderecoRequest = EnderecoRequest.builder()
                .cep(cep)
                .numero(numero)
                .complemento(complemento)
                .build();

        // Ação
        Endereco endereco = enderecoRequest.construirEndereco(viaCepSerive);

        // Verificações
        assertNotNull(endereco);
        assertEquals("12345-678", endereco.getCep());
        assertEquals("Rua Teste", endereco.getLogradouro());
        assertEquals("Bairro Teste", endereco.getBairro());
        assertEquals("10", endereco.getNumero());
        assertEquals("Cidade Teste", endereco.getLocalidade());
        assertEquals("SP", endereco.getUf());
        assertEquals("São Paulo", endereco.getEstado());
        assertEquals("Apt 101", endereco.getComplemento());

        // Verifica se o serviço foi chamado corretamente
        verify(viaCepSerive, times(1)).buscarEnderecoPorCep(cep, numero, complemento);
    }

    @Test
    void deveConstruirEnderecoSemViaCepService() {
        // Configuração do ambiente de teste
        EnderecoRequest enderecoRequest = EnderecoRequest.builder()
                .cep("98765-432")
                .logradouro("Avenida Teste")
                .bairro("Outro Bairro")
                .numero("20")
                .localidade("Outra Cidade")
                .uf("RJ")
                .estado("Rio de Janeiro")
                .complemento("Casa 2")
                .build();

        // Ação
        Endereco endereco = enderecoRequest.construirEndereco();

        // Verificações
        assertNotNull(endereco);
        assertEquals("98765-432", endereco.getCep());
        assertEquals("Avenida Teste", endereco.getLogradouro());
        assertEquals("Outro Bairro", endereco.getBairro());
        assertEquals("20", endereco.getNumero());
        assertEquals("Outra Cidade", endereco.getLocalidade());
        assertEquals("RJ", endereco.getUf());
        assertEquals("Rio de Janeiro", endereco.getEstado());
        assertEquals("Casa 2", endereco.getComplemento());
    }
}
