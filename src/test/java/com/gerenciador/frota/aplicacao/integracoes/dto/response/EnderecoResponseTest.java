package com.gerenciador.frota.aplicacao.integracoes.dto.response;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoResponseTest {

    @Test
    void deveCriarEnderecoComNumero() {
        // Configuração
        EnderecoResponse enderecoResponse = new EnderecoResponse(
                "12345678", "Rua Teste", "Bairro Teste", "Cidade Teste", "UF", "Estado Teste", "Apt 202"
        );
        String numero = "100";

        // Ação
        Endereco endereco = enderecoResponse.getEndereco(numero);

        // Verificação
        assertNotNull(endereco);
        assertEquals("12345678", endereco.getCep());
        assertEquals("Rua Teste", endereco.getLogradouro());
        assertEquals("Bairro Teste", endereco.getBairro());
        assertEquals("Cidade Teste", endereco.getLocalidade());
        assertEquals("UF", endereco.getUf());
        assertEquals("Estado Teste", endereco.getEstado());
        assertEquals("Apt 202", endereco.getComplemento());
        assertEquals("100", endereco.getNumero());
    }

    @Test
    void deveCriarEnderecoComNumeroEComplemento() {
        // Configuração
        EnderecoResponse enderecoResponse = new EnderecoResponse(
                "12345678", "Rua Teste", "Bairro Teste", "Cidade Teste", "UF", "Estado Teste", "Apt 202"
        );
        String numero = "200";
        String complemento = "Bloco B";

        // Ação
        Endereco endereco = enderecoResponse.construirEndereco(numero, complemento);

        // Verificação
        assertNotNull(endereco);
        assertEquals("12345678", endereco.getCep());
        assertEquals("Rua Teste", endereco.getLogradouro());
        assertEquals("Bairro Teste", endereco.getBairro());
        assertEquals("Cidade Teste", endereco.getLocalidade());
        assertEquals("UF", endereco.getUf());
        assertEquals("Estado Teste", endereco.getEstado());
        assertEquals("Bloco B", endereco.getComplemento());
        assertEquals("200", endereco.getNumero());
    }

    @Test
    void deveCriarEnderecoComComplementoVazio() {
        // Configuração
        EnderecoResponse enderecoResponse = new EnderecoResponse(
                "12345678", "Rua Teste", "Bairro Teste", "Cidade Teste", "UF", "Estado Teste", ""
        );
        String numero = "300";
        String complemento = "";

        // Ação
        Endereco endereco = enderecoResponse.construirEndereco(numero, complemento);

        // Verificação
        assertNotNull(endereco);
        assertEquals("12345678", endereco.getCep());
        assertEquals("Rua Teste", endereco.getLogradouro());
        assertEquals("Bairro Teste", endereco.getBairro());
        assertEquals("Cidade Teste", endereco.getLocalidade());
        assertEquals("UF", endereco.getUf());
        assertEquals("Estado Teste", endereco.getEstado());
        assertEquals("", endereco.getComplemento());
        assertEquals("300", endereco.getNumero());
    }
}
