package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrecoProdutoRequestTest {

    private PrecoProdutoRequest precoProdutoRequest;

    @BeforeEach
    void setUp() {
        // Inicializa a classe para os testes
        precoProdutoRequest = new PrecoProdutoRequest();
    }

    @Test
    void getValorLiquido() {
        precoProdutoRequest.setValorLiquido(50.75);
        assertEquals(50.75, precoProdutoRequest.getValorLiquido());
    }

    @Test
    void getValorBruto() {
        precoProdutoRequest.setValorBruto(60.25);
        assertEquals(60.25, precoProdutoRequest.getValorBruto());
    }

    @Test
    void setValorLiquido() {
        precoProdutoRequest.setValorLiquido(45.00);
        assertEquals(45.00, precoProdutoRequest.getValorLiquido());
    }

    @Test
    void setValorBruto() {
        precoProdutoRequest.setValorBruto(55.00);
        assertEquals(55.00, precoProdutoRequest.getValorBruto());
    }

    @Test
    void builder() {
        // Testa a construção da classe usando o Builder
        PrecoProdutoRequest preco = PrecoProdutoRequest.builder()
                .valorLiquido(100.50)
                .valorBruto(120.75)
                .build();

        assertNotNull(preco);
        assertEquals(100.50, preco.getValorLiquido());
        assertEquals(120.75, preco.getValorBruto());
    }

    @Test
    void allArgsConstructor() {
        // Testa o construtor com todos os argumentos
        PrecoProdutoRequest preco = new PrecoProdutoRequest(80.0, 90.0);

        assertNotNull(preco);
        assertEquals(80.0, preco.getValorLiquido());
        assertEquals(90.0, preco.getValorBruto());
    }

    @Test
    void noArgsConstructor() {
        // Testa o construtor sem argumentos
        PrecoProdutoRequest preco = new PrecoProdutoRequest();

        assertNotNull(preco);
        assertNull(preco.getValorLiquido());
        assertNull(preco.getValorBruto());
    }
}
