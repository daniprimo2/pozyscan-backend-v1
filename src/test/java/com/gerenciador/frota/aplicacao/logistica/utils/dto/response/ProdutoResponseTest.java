package com.gerenciador.frota.aplicacao.logistica.utils.dto.response;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoResponseTest {

    private ProdutoResponse produtoResponse;

    @BeforeEach
    void setUp() {
        // Inicializa a classe para os testes
        produtoResponse = new ProdutoResponse();
    }

    @Test
    void getNomeProduto() {
        produtoResponse.setNomeProduto("Produto Teste");
        assertEquals("Produto Teste", produtoResponse.getNomeProduto());
    }

    @Test
    void getDescricaoProduto() {
        produtoResponse.setDescricaoProduto("Descrição do Produto Teste");
        assertEquals("Descrição do Produto Teste", produtoResponse.getDescricaoProduto());
    }

    @Test
    void getTipoProduto() {
        produtoResponse.setTipoProduto(TipoProduto.UNIDADE);
        assertEquals(TipoProduto.UNIDADE, produtoResponse.getTipoProduto());
    }

    @Test
    void getPesoLiquido() {
        produtoResponse.setPesoLiquido(2.5);
        assertEquals(2.5, produtoResponse.getPesoLiquido());
    }

    @Test
    void getPesoBruto() {
        produtoResponse.setPesoBruto(3.0);
        assertEquals(3.0, produtoResponse.getPesoBruto());
    }

    @Test
    void getQuantidade() {
        produtoResponse.setQuantidade(10);
        assertEquals(10, produtoResponse.getQuantidade());
    }

    @Test
    void getValorLiquido() {
        produtoResponse.setValorLiquido(100.0);
        assertEquals(100.0, produtoResponse.getValorLiquido());
    }

    @Test
    void getValorBruto() {
        produtoResponse.setValorBruto(120.0);
        assertEquals(120.0, produtoResponse.getValorBruto());
    }

    @Test
    void getLargura() {
        produtoResponse.setLargura(10.0);
        assertEquals(10.0, produtoResponse.getLargura());
    }

    @Test
    void getAltura() {
        produtoResponse.setAltura(20.0);
        assertEquals(20.0, produtoResponse.getAltura());
    }

    @Test
    void getComprimento() {
        produtoResponse.setComprimento(30.0);
        assertEquals(30.0, produtoResponse.getComprimento());
    }

    @Test
    void setNomeProduto() {
        produtoResponse.setNomeProduto("Novo Produto");
        assertEquals("Novo Produto", produtoResponse.getNomeProduto());
    }

    @Test
    void setDescricaoProduto() {
        produtoResponse.setDescricaoProduto("Nova Descrição");
        assertEquals("Nova Descrição", produtoResponse.getDescricaoProduto());
    }

    @Test
    void setTipoProduto() {
        produtoResponse.setTipoProduto(TipoProduto.UNIDADE);
        assertEquals(TipoProduto.UNIDADE, produtoResponse.getTipoProduto());
    }

    @Test
    void setPesoLiquido() {
        produtoResponse.setPesoLiquido(4.5);
        assertEquals(4.5, produtoResponse.getPesoLiquido());
    }

    @Test
    void setPesoBruto() {
        produtoResponse.setPesoBruto(5.0);
        assertEquals(5.0, produtoResponse.getPesoBruto());
    }

    @Test
    void setQuantidade() {
        produtoResponse.setQuantidade(50);
        assertEquals(50, produtoResponse.getQuantidade());
    }

    @Test
    void setValorLiquido() {
        produtoResponse.setValorLiquido(150.0);
        assertEquals(150.0, produtoResponse.getValorLiquido());
    }

    @Test
    void setValorBruto() {
        produtoResponse.setValorBruto(200.0);
        assertEquals(200.0, produtoResponse.getValorBruto());
    }

    @Test
    void setLargura() {
        produtoResponse.setLargura(15.0);
        assertEquals(15.0, produtoResponse.getLargura());
    }

    @Test
    void setAltura() {
        produtoResponse.setAltura(25.0);
        assertEquals(25.0, produtoResponse.getAltura());
    }

    @Test
    void setComprimento() {
        produtoResponse.setComprimento(35.0);
        assertEquals(35.0, produtoResponse.getComprimento());
    }

    @Test
    void builder() {
        // Testa a construção da classe usando o Builder
        ProdutoResponse produto = ProdutoResponse.builder()
                .nomeProduto("Produto Builder")
                .descricaoProduto("Descrição Builder")
                .tipoProduto(TipoProduto.UNIDADE)
                .pesoLiquido(1.0)
                .pesoBruto(1.2)
                .quantidade(5)
                .valorLiquido(50.0)
                .valorBruto(60.0)
                .largura(10.0)
                .altura(20.0)
                .comprimento(30.0)
                .build();

        assertNotNull(produto);
        assertEquals("Produto Builder", produto.getNomeProduto());
        assertEquals("Descrição Builder", produto.getDescricaoProduto());
        assertEquals(TipoProduto.UNIDADE, produto.getTipoProduto());
        assertEquals(1.0, produto.getPesoLiquido());
        assertEquals(1.2, produto.getPesoBruto());
        assertEquals(5, produto.getQuantidade());
        assertEquals(50.0, produto.getValorLiquido());
        assertEquals(60.0, produto.getValorBruto());
        assertEquals(10.0, produto.getLargura());
        assertEquals(20.0, produto.getAltura());
        assertEquals(30.0, produto.getComprimento());
    }
}
