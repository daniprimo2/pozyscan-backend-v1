package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DadosPessoaisTest {

    private DadosPessoais dadosPessoais;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        endereco = new Endereco(
                6L,
                "06385820",
                "Rua Ipixuna",
                "154",
                "Vila Menk",
                "Carapicuíba",
                "SP",
                "São Paulo",
                ""
        );
        dadosPessoais = new DadosPessoais(
                2L,
                "Daniel Lopes Sousa Moreira",
                "05/08/2024",
                "Eronice Cristina",
                "Paulo Roberto",
                endereco
        );
    }

    @Test
    void deveCriarDadosPessoaisComSucesso() {
        assertNotNull(dadosPessoais);
        assertEquals(2L, dadosPessoais.getId());
        assertEquals("Daniel Lopes Sousa Moreira", dadosPessoais.getNomeCompleto());
        assertEquals("05/08/2024", dadosPessoais.getDataNascimento());
        assertEquals("Eronice Cristina", dadosPessoais.getNomeMae());
        assertEquals("Paulo Roberto", dadosPessoais.getNomePai());
        assertEquals(endereco, dadosPessoais.getEndereco());
    }

    @Test
    void deveAtualizarNomeCompletoComSucesso() {
        dadosPessoais.setNomeCompleto("Maria Oliveira");
        assertEquals("Maria Oliveira", dadosPessoais.getNomeCompleto());
    }

    @Test
    void deveAtualizarDataNascimentoComSucesso() {
        dadosPessoais.setDataNascimento("01/01/1990");
        assertEquals("01/01/1990", dadosPessoais.getDataNascimento());
    }

    @Test
    void deveAtualizarNomeMaeComSucesso() {
        dadosPessoais.setNomeMae("Cristina Silva");
        assertEquals("Cristina Silva", dadosPessoais.getNomeMae());
    }

    @Test
    void deveAtualizarNomePaiComSucesso() {
        dadosPessoais.setNomePai("Roberto Oliveira");
        assertEquals("Roberto Oliveira", dadosPessoais.getNomePai());
    }

    @Test
    void deveAtualizarEnderecoComSucesso() {
        Endereco novoEndereco = new Endereco(
                10L,
                "01001000",
                "Praça da Sé",
                "S/N",
                "Sé",
                "São Paulo",
                "SP",
                "São Paulo",
                ""
        );
        dadosPessoais.setEndereco(novoEndereco);
        assertEquals(novoEndereco, dadosPessoais.getEndereco());
    }

    @Test
    void deveCriarDadosPessoaisUsandoConstrutorVazio() {
        DadosPessoais novosDadosPessoais = new DadosPessoais();
        assertNull(novosDadosPessoais.getId());
        assertNull(novosDadosPessoais.getNomeCompleto());
        assertNull(novosDadosPessoais.getDataNascimento());
        assertNull(novosDadosPessoais.getNomeMae());
        assertNull(novosDadosPessoais.getNomePai());
        assertNull(novosDadosPessoais.getEndereco());
    }
}
