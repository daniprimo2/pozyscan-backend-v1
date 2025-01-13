package com.gerenciador.frota.aplicacao.logistica.dominio.model;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private Produto produto;
    private NotaFiscalLogistica notaFiscalLogistica;
    private Remessa remessa;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        // Setup de objetos necessários
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
        remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        notaFiscalLogistica = new NotaFiscalLogistica(1L, "NF123", 1000.0, "2025-01-12", remessa, endereco);

        // Setup do Produto
        produto = new Produto(
                1L, "Produto Teste", "Produto para testes", TipoProduto.UNIDADE,
                10.0, 12.0, 100, 200.0, 250.0, 20.0, 10.0, 5.0, notaFiscalLogistica
        );
    }

    @Test
    void testGetCodigoProduto() {
        assertEquals(1L, produto.getCodigoProduto());
    }

    @Test
    void testSetCodigoProduto() {
        produto.setCodigoProduto(2L);
        assertEquals(2L, produto.getCodigoProduto());
    }

    @Test
    void testGetNomeProduto() {
        assertEquals("Produto Teste", produto.getNomeProduto());
    }

    @Test
    void testSetNomeProduto() {
        produto.setNomeProduto("Novo Produto");
        assertEquals("Novo Produto", produto.getNomeProduto());
    }

    @Test
    void testGetDescricaoProduto() {
        assertEquals("Produto para testes", produto.getDescricaoProduto());
    }

    @Test
    void testSetDescricaoProduto() {
        produto.setDescricaoProduto("Descrição atualizada");
        assertEquals("Descrição atualizada", produto.getDescricaoProduto());
    }

    @Test
    void testGetTipoProduto() {
        assertEquals(TipoProduto.UNIDADE, produto.getTipoProduto());
    }

    @Test
    void testSetTipoProduto() {
        produto.setTipoProduto(TipoProduto.PACOTE_FECHADO);
        assertEquals(TipoProduto.PACOTE_FECHADO, produto.getTipoProduto());
    }

    @Test
    void testGetPesoLiquido() {
        assertEquals(10.0, produto.getPesoLiquido());
    }

    @Test
    void testSetPesoLiquido() {
        produto.setPesoLiquido(12.0);
        assertEquals(12.0, produto.getPesoLiquido());
    }

    @Test
    void testGetPesoBruto() {
        assertEquals(12.0, produto.getPesoBruto());
    }

    @Test
    void testSetPesoBruto() {
        produto.setPesoBruto(15.0);
        assertEquals(15.0, produto.getPesoBruto());
    }

    @Test
    void testGetQuantidade() {
        assertEquals(100, produto.getQuantidade());
    }

    @Test
    void testSetQuantidade() {
        produto.setQuantidade(200);
        assertEquals(200, produto.getQuantidade());
    }

    @Test
    void testGetValorLiquido() {
        assertEquals(200.0, produto.getValorLiquido());
    }

    @Test
    void testSetValorLiquido() {
        produto.setValorLiquido(300.0);
        assertEquals(300.0, produto.getValorLiquido());
    }

    @Test
    void testGetValorBruto() {
        assertEquals(250.0, produto.getValorBruto());
    }

    @Test
    void testSetValorBruto() {
        produto.setValorBruto(350.0);
        assertEquals(350.0, produto.getValorBruto());
    }

    @Test
    void testGetLargura() {
        assertEquals(20.0, produto.getLargura());
    }

    @Test
    void testSetLargura() {
        produto.setLargura(25.0);
        assertEquals(25.0, produto.getLargura());
    }

    @Test
    void testGetAltura() {
        assertEquals(10.0, produto.getAltura());
    }

    @Test
    void testSetAltura() {
        produto.setAltura(15.0);
        assertEquals(15.0, produto.getAltura());
    }

    @Test
    void testGetComprimento() {
        assertEquals(5.0, produto.getComprimento());
    }

    @Test
    void testSetComprimento() {
        produto.setComprimento(6.0);
        assertEquals(6.0, produto.getComprimento());
    }

    @Test
    void testGetNotaFiscalLogistica() {
        assertNotNull(produto.getNotaFiscalLogistica());
    }

    @Test
    void testSetNotaFiscalLogistica() {
        NotaFiscalLogistica novaNotaFiscal = new NotaFiscalLogistica(2L, "NF124", 2000.0, "2025-01-13", remessa, endereco);
        produto.setNotaFiscalLogistica(novaNotaFiscal);
        assertEquals("NF124", produto.getNotaFiscalLogistica().getNumeroNotaFisal());
    }
}
