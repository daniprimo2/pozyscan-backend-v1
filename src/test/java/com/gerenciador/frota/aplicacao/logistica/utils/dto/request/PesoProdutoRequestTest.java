package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PesoProdutoRequestTest {

    private PesoProdutoRequest pesoProdutoRequest;

    @BeforeEach
    void setUp() {
        // Inicializa a classe para os testes
        pesoProdutoRequest = new PesoProdutoRequest();
    }

    @Test
    void getPesoLiquido() {
        pesoProdutoRequest.setPesoLiquido(12.5);
        assertEquals(12.5, pesoProdutoRequest.getPesoLiquido());
    }

    @Test
    void getPesoBruto() {
        pesoProdutoRequest.setPesoBruto(15.0);
        assertEquals(15.0, pesoProdutoRequest.getPesoBruto());
    }

    @Test
    void setPesoLiquido() {
        pesoProdutoRequest.setPesoLiquido(18.2);
        assertEquals(18.2, pesoProdutoRequest.getPesoLiquido());
    }

    @Test
    void setPesoBruto() {
        pesoProdutoRequest.setPesoBruto(20.0);
        assertEquals(20.0, pesoProdutoRequest.getPesoBruto());
    }

    @Test
    void builder() {
        // Testa a construção da classe usando o Builder
        PesoProdutoRequest produto = PesoProdutoRequest.builder()
                .pesoLiquido(10.0)
                .pesoBruto(12.0)
                .build();

        assertNotNull(produto);
        assertEquals(10.0, produto.getPesoLiquido());
        assertEquals(12.0, produto.getPesoBruto());
    }

    @Test
    void allArgsConstructor() {
        // Testa o construtor com todos os argumentos
        PesoProdutoRequest produto = new PesoProdutoRequest(7.0, 9.5);

        assertNotNull(produto);
        assertEquals(7.0, produto.getPesoLiquido());
        assertEquals(9.5, produto.getPesoBruto());
    }

    @Test
    void noArgsConstructor() {
        // Testa o construtor sem argumentos
        PesoProdutoRequest produto = new PesoProdutoRequest();

        assertNotNull(produto);
        assertNull(produto.getPesoLiquido());
        assertNull(produto.getPesoBruto());
    }
}
