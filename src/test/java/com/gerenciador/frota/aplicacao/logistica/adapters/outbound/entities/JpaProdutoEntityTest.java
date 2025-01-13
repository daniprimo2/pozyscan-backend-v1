package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JpaProdutoEntityTest {

    private JpaProdutoEntity produtoEntity;
    private JpaNotaFiscalLogisticaEntity notaFiscalLogisticaEntity;

    @BeforeEach
    void setUp() {
        // Setup da NotaFiscalLogisticaEntity
        notaFiscalLogisticaEntity = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(1L)
                .numeroNotaFisal("NF123")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-12")
                .build();

        // Setup da JpaProdutoEntity
        produtoEntity = JpaProdutoEntity.builder()
                .codigoProduto(1L)
                .nomeProduto("Produto Teste")
                .descricaoProduto("Produto para testes")
                .tipoProduto(TipoProduto.UNIDADE)
                .pesoLiquido(10.0)
                .pesoBruto(12.0)
                .quantidade(100)
                .valorLiquido(200.0)
                .valorBruto(250.0)
                .largura(20.0)
                .altura(10.0)
                .comprimento(5.0)
                .jpaNotaFiscalLogisticaEntity(notaFiscalLogisticaEntity)
                .build();
    }

    @Test
    void testGetCodigoProduto() {
        assertEquals(1L, produtoEntity.getCodigoProduto());
    }

    @Test
    void testSetCodigoProduto() {
        produtoEntity.setCodigoProduto(2L);
        assertEquals(2L, produtoEntity.getCodigoProduto());
    }

    @Test
    void testGetNomeProduto() {
        assertEquals("Produto Teste", produtoEntity.getNomeProduto());
    }

    @Test
    void testSetNomeProduto() {
        produtoEntity.setNomeProduto("Novo Produto");
        assertEquals("Novo Produto", produtoEntity.getNomeProduto());
    }

    @Test
    void testGetDescricaoProduto() {
        assertEquals("Produto para testes", produtoEntity.getDescricaoProduto());
    }

    @Test
    void testSetDescricaoProduto() {
        produtoEntity.setDescricaoProduto("Descrição atualizada");
        assertEquals("Descrição atualizada", produtoEntity.getDescricaoProduto());
    }

    @Test
    void testGetTipoProduto() {
        assertEquals(TipoProduto.UNIDADE, produtoEntity.getTipoProduto());
    }

    @Test
    void testSetTipoProduto() {
        produtoEntity.setTipoProduto(TipoProduto.PACOTE_FECHADO);
        assertEquals(TipoProduto.PACOTE_FECHADO, produtoEntity.getTipoProduto());
    }

    @Test
    void testGetPesoLiquido() {
        assertEquals(10.0, produtoEntity.getPesoLiquido());
    }

    @Test
    void testSetPesoLiquido() {
        produtoEntity.setPesoLiquido(12.0);
        assertEquals(12.0, produtoEntity.getPesoLiquido());
    }

    @Test
    void testGetPesoBruto() {
        assertEquals(12.0, produtoEntity.getPesoBruto());
    }

    @Test
    void testSetPesoBruto() {
        produtoEntity.setPesoBruto(15.0);
        assertEquals(15.0, produtoEntity.getPesoBruto());
    }

    @Test
    void testGetQuantidade() {
        assertEquals(100, produtoEntity.getQuantidade());
    }

    @Test
    void testSetQuantidade() {
        produtoEntity.setQuantidade(200);
        assertEquals(200, produtoEntity.getQuantidade());
    }

    @Test
    void testGetValorLiquido() {
        assertEquals(200.0, produtoEntity.getValorLiquido());
    }

    @Test
    void testSetValorLiquido() {
        produtoEntity.setValorLiquido(300.0);
        assertEquals(300.0, produtoEntity.getValorLiquido());
    }

    @Test
    void testGetValorBruto() {
        assertEquals(250.0, produtoEntity.getValorBruto());
    }

    @Test
    void testSetValorBruto() {
        produtoEntity.setValorBruto(350.0);
        assertEquals(350.0, produtoEntity.getValorBruto());
    }

    @Test
    void testGetLargura() {
        assertEquals(20.0, produtoEntity.getLargura());
    }

    @Test
    void testSetLargura() {
        produtoEntity.setLargura(25.0);
        assertEquals(25.0, produtoEntity.getLargura());
    }

    @Test
    void testGetAltura() {
        assertEquals(10.0, produtoEntity.getAltura());
    }

    @Test
    void testSetAltura() {
        produtoEntity.setAltura(15.0);
        assertEquals(15.0, produtoEntity.getAltura());
    }

    @Test
    void testGetComprimento() {
        assertEquals(5.0, produtoEntity.getComprimento());
    }

    @Test
    void testSetComprimento() {
        produtoEntity.setComprimento(6.0);
        assertEquals(6.0, produtoEntity.getComprimento());
    }

    @Test
    void testGetJpaNotaFiscalLogisticaEntity() {
        assertNotNull(produtoEntity.getJpaNotaFiscalLogisticaEntity());
        assertEquals("NF123", produtoEntity.getJpaNotaFiscalLogisticaEntity().getNumeroNotaFisal());
    }

    @Test
    void testSetJpaNotaFiscalLogisticaEntity() {
        JpaNotaFiscalLogisticaEntity novaNotaFiscal = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(2L)
                .numeroNotaFisal("NF124")
                .valorTotal(2000.0)
                .dataEmissao("2025-01-13")
                .build();
        produtoEntity.setJpaNotaFiscalLogisticaEntity(novaNotaFiscal);
        assertEquals("NF124", produtoEntity.getJpaNotaFiscalLogisticaEntity().getNumeroNotaFisal());
    }

    @Test
    void testBuilder() {
        JpaProdutoEntity produtoComBuilder = JpaProdutoEntity.builder()
                .codigoProduto(2L)
                .nomeProduto("Produto Teste Builder")
                .descricaoProduto("Descrição do produto com builder")
                .tipoProduto(TipoProduto.PACOTE_FECHADO)
                .pesoLiquido(20.0)
                .pesoBruto(25.0)
                .quantidade(50)
                .valorLiquido(500.0)
                .valorBruto(600.0)
                .largura(30.0)
                .altura(15.0)
                .comprimento(8.0)
                .jpaNotaFiscalLogisticaEntity(notaFiscalLogisticaEntity)
                .build();

        assertNotNull(produtoComBuilder);
        assertEquals("Produto Teste Builder", produtoComBuilder.getNomeProduto());
    }
}
