package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoRequestTest {

    private ProdutoRequest produtoRequest;

    @BeforeEach
    void setUp() {
        // Inicializa a classe ProdutoRequest para os testes
        produtoRequest = new ProdutoRequest();
    }

    @Test
    void getNomeProduto() {
        produtoRequest.setNomeProduto("Produto Teste");
        assertEquals("Produto Teste", produtoRequest.getNomeProduto());
    }

    @Test
    void getDescricaoProduto() {
        produtoRequest.setDescricaoProduto("Descrição do Produto Teste");
        assertEquals("Descrição do Produto Teste", produtoRequest.getDescricaoProduto());
    }

    @Test
    void getTipoProduto() {
        produtoRequest.setTipoProduto(TipoProduto.UNIDADE);
        assertEquals(TipoProduto.UNIDADE, produtoRequest.getTipoProduto());
    }

    @Test
    void getQuantidade() {
        produtoRequest.setQuantidade(10);
        assertEquals(10, produtoRequest.getQuantidade());
    }

    @Test
    void getPesoProdutoRequest() {
        PesoProdutoRequest pesoProduto = PesoProdutoRequest.builder()
                .pesoLiquido(2.5)
                .pesoBruto(3.0)
                .build();
        produtoRequest.setPesoProdutoRequest(pesoProduto);

        assertNotNull(produtoRequest.getPesoProdutoRequest());
        assertEquals(2.5, produtoRequest.getPesoProdutoRequest().getPesoLiquido());
        assertEquals(3.0, produtoRequest.getPesoProdutoRequest().getPesoBruto());
    }

    @Test
    void getDimensaoProdutoRequest() {
        DimensaoProdutoRequest dimensaoProduto = DimensaoProdutoRequest.builder()
                .largura(10.0)
                .altura(20.0)
                .comprimento(30.0)
                .build();
        produtoRequest.setDimensaoProdutoRequest(dimensaoProduto);

        assertNotNull(produtoRequest.getDimensaoProdutoRequest());
        assertEquals(10.0, produtoRequest.getDimensaoProdutoRequest().getLargura());
        assertEquals(20.0, produtoRequest.getDimensaoProdutoRequest().getAltura());
        assertEquals(30.0, produtoRequest.getDimensaoProdutoRequest().getComprimento());
    }

    @Test
    void getPrecoProdutoRequest() {
        PrecoProdutoRequest precoProduto = PrecoProdutoRequest.builder()
                .valorLiquido(100.0)
                .valorBruto(120.0)
                .build();
        produtoRequest.setPrecoProdutoRequest(precoProduto);

        assertNotNull(produtoRequest.getPrecoProdutoRequest());
        assertEquals(100.0, produtoRequest.getPrecoProdutoRequest().getValorLiquido());
        assertEquals(120.0, produtoRequest.getPrecoProdutoRequest().getValorBruto());
    }

    @Test
    void setNomeProduto() {
        produtoRequest.setNomeProduto("Novo Produto");
        assertEquals("Novo Produto", produtoRequest.getNomeProduto());
    }

    @Test
    void setDescricaoProduto() {
        produtoRequest.setDescricaoProduto("Nova Descrição");
        assertEquals("Nova Descrição", produtoRequest.getDescricaoProduto());
    }

    @Test
    void setTipoProduto() {
        produtoRequest.setTipoProduto(TipoProduto.UNIDADE);
        assertEquals(TipoProduto.UNIDADE, produtoRequest.getTipoProduto());
    }

    @Test
    void setQuantidade() {
        produtoRequest.setQuantidade(50);
        assertEquals(50, produtoRequest.getQuantidade());
    }

    @Test
    void setPesoProdutoRequest() {
        PesoProdutoRequest pesoProduto = new PesoProdutoRequest(1.5, 2.0);
        produtoRequest.setPesoProdutoRequest(pesoProduto);

        assertNotNull(produtoRequest.getPesoProdutoRequest());
        assertEquals(1.5, produtoRequest.getPesoProdutoRequest().getPesoLiquido());
        assertEquals(2.0, produtoRequest.getPesoProdutoRequest().getPesoBruto());
    }

    @Test
    void setDimensaoProdutoRequest() {
        DimensaoProdutoRequest dimensaoProduto = new DimensaoProdutoRequest(5.0, 10.0, 15.0);
        produtoRequest.setDimensaoProdutoRequest(dimensaoProduto);

        assertNotNull(produtoRequest.getDimensaoProdutoRequest());
        assertEquals(5.0, produtoRequest.getDimensaoProdutoRequest().getLargura());
        assertEquals(10.0, produtoRequest.getDimensaoProdutoRequest().getAltura());
        assertEquals(15.0, produtoRequest.getDimensaoProdutoRequest().getComprimento());
    }

    @Test
    void setPrecoProdutoRequest() {
        PrecoProdutoRequest precoProduto = new PrecoProdutoRequest(200.0, 250.0);
        produtoRequest.setPrecoProdutoRequest(precoProduto);

        assertNotNull(produtoRequest.getPrecoProdutoRequest());
        assertEquals(200.0, produtoRequest.getPrecoProdutoRequest().getValorLiquido());
        assertEquals(250.0, produtoRequest.getPrecoProdutoRequest().getValorBruto());
    }

    @Test
    void builder() {
        ProdutoRequest produto = ProdutoRequest.builder()
                .nomeProduto("Produto Builder")
                .descricaoProduto("Descrição do Produto Builder")
                .tipoProduto(TipoProduto.PACOTE_FECHADO)
                .quantidade(5)
                .pesoProdutoRequest(new PesoProdutoRequest(1.0, 1.2))
                .dimensaoProdutoRequest(new DimensaoProdutoRequest(10.0, 20.0, 30.0))
                .precoProdutoRequest(new PrecoProdutoRequest(50.0, 60.0))
                .build();

        assertNotNull(produto);
        assertEquals("Produto Builder", produto.getNomeProduto());
        assertEquals("Descrição do Produto Builder", produto.getDescricaoProduto());
        assertEquals(TipoProduto.PACOTE_FECHADO, produto.getTipoProduto());
        assertEquals(5, produto.getQuantidade());
        assertEquals(1.0, produto.getPesoProdutoRequest().getPesoLiquido());
        assertEquals(1.2, produto.getPesoProdutoRequest().getPesoBruto());
        assertEquals(10.0, produto.getDimensaoProdutoRequest().getLargura());
        assertEquals(20.0, produto.getDimensaoProdutoRequest().getAltura());
        assertEquals(30.0, produto.getDimensaoProdutoRequest().getComprimento());
        assertEquals(50.0, produto.getPrecoProdutoRequest().getValorLiquido());
        assertEquals(60.0, produto.getPrecoProdutoRequest().getValorBruto());
    }
}
