package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensaoProdutoRequestTest {

    private DimensaoProdutoRequest dimensaoProdutoRequest;

    @BeforeEach
    void setUp() {
        // Inicializa a classe para os testes
        dimensaoProdutoRequest = new DimensaoProdutoRequest();
    }

    @Test
    void getLargura() {
        dimensaoProdutoRequest.setLargura(15.0);
        assertEquals(15.0, dimensaoProdutoRequest.getLargura());
    }

    @Test
    void getAltura() {
        dimensaoProdutoRequest.setAltura(10.0);
        assertEquals(10.0, dimensaoProdutoRequest.getAltura());
    }

    @Test
    void getComprimento() {
        dimensaoProdutoRequest.setComprimento(20.0);
        assertEquals(20.0, dimensaoProdutoRequest.getComprimento());
    }

    @Test
    void setLargura() {
        dimensaoProdutoRequest.setLargura(25.0);
        assertEquals(25.0, dimensaoProdutoRequest.getLargura());
    }

    @Test
    void setAltura() {
        dimensaoProdutoRequest.setAltura(30.0);
        assertEquals(30.0, dimensaoProdutoRequest.getAltura());
    }

    @Test
    void setComprimento() {
        dimensaoProdutoRequest.setComprimento(40.0);
        assertEquals(40.0, dimensaoProdutoRequest.getComprimento());
    }

    @Test
    void builder() {
        // Testa a construção da classe usando o Builder
        DimensaoProdutoRequest produto = DimensaoProdutoRequest.builder()
                .largura(10.0)
                .altura(5.0)
                .comprimento(15.0)
                .build();

        assertNotNull(produto);
        assertEquals(10.0, produto.getLargura());
        assertEquals(5.0, produto.getAltura());
        assertEquals(15.0, produto.getComprimento());
    }

    @Test
    void allArgsConstructor() {
        // Testa o construtor com todos os argumentos
        DimensaoProdutoRequest produto = new DimensaoProdutoRequest(5.0, 10.0, 20.0);

        assertNotNull(produto);
        assertEquals(5.0, produto.getLargura());
        assertEquals(10.0, produto.getAltura());
        assertEquals(20.0, produto.getComprimento());
    }

    @Test
    void noArgsConstructor() {
        // Testa o construtor sem argumentos
        DimensaoProdutoRequest produto = new DimensaoProdutoRequest();

        assertNotNull(produto);
        assertNull(produto.getLargura());
        assertNull(produto.getAltura());
        assertNull(produto.getComprimento());
    }
}
