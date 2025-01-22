package com.gerenciador.frota.aplicacao.logistica.dominio.model;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotaFiscalLogisticaTest {

    private NotaFiscalLogistica notaFiscalLogistica;

    private static final Long CODIGO_NOTA_FISCAL = 1L;
    private static final String NUMERO_NOTA_FISCAL = "NF-12345";
    private static final Double VALOR_TOTAL = 1500.00;
    private static final String DATA_EMISSAO = "2025-01-13";
    private static final Remessa REMESSA = new Remessa(); // Presumindo que Remessa seja outra classe que você tenha
    private static final Endereco ENDERECO =  new Endereco(
            6l,
            "06385820",
            "Rua Ipixuna",
            "154",
            "Vila Menk",
            "Carapicuiba",
            "SP",
            "São Paulo",
            "");
    @BeforeEach
    void setUp() {
        notaFiscalLogistica = new NotaFiscalLogistica(
                CODIGO_NOTA_FISCAL,
                NUMERO_NOTA_FISCAL,
                VALOR_TOTAL,
                DATA_EMISSAO,
                REMESSA,
                ENDERECO
        );
    }

    @Test
    void testGetCodigoNotaFiscal() {
        assertEquals(CODIGO_NOTA_FISCAL, notaFiscalLogistica.getCodigoNotaFiscal());
    }

    @Test
    void testSetCodigoNotaFiscal() {
        Long novoCodigo = 2L;
        notaFiscalLogistica.setCodigoNotaFiscal(novoCodigo);
        assertEquals(novoCodigo, notaFiscalLogistica.getCodigoNotaFiscal());
    }

    @Test
    void testGetNumeroNotaFisal() {
        assertEquals(NUMERO_NOTA_FISCAL, notaFiscalLogistica.getNumeroNotaFisal());
    }

    @Test
    void testSetNumeroNotaFisal() {
        String novoNumeroNota = "NF-54321";
        notaFiscalLogistica.setNumeroNotaFisal(novoNumeroNota);
        assertEquals(novoNumeroNota, notaFiscalLogistica.getNumeroNotaFisal());
    }

    @Test
    void testGetValorTotal() {
        assertEquals(VALOR_TOTAL, notaFiscalLogistica.getValorTotal());
    }

    @Test
    void testSetValorTotal() {
        Double novoValor = 2000.00;
        notaFiscalLogistica.setValorTotal(novoValor);
        assertEquals(novoValor, notaFiscalLogistica.getValorTotal());
    }

    @Test
    void testGetDataEmissao() {
        assertEquals(DATA_EMISSAO, notaFiscalLogistica.getDataEmissao());
    }

    @Test
    void testSetDataEmissao() {
        String novaData = "2025-01-14";
        notaFiscalLogistica.setDataEmissao(novaData);
        assertEquals(novaData, notaFiscalLogistica.getDataEmissao());
    }

    @Test
    void testGetRemessa() {
        assertEquals(REMESSA, notaFiscalLogistica.getRemessa());
    }

    @Test
    void testSetRemessa() {
        Remessa novaRemessa = new Remessa(); // Criação de uma nova instância para testar o setter
        notaFiscalLogistica.setRemessa(novaRemessa);
        assertEquals(novaRemessa, notaFiscalLogistica.getRemessa());
    }

    @Test
    void testGetEndereco() {
        assertEquals(ENDERECO, notaFiscalLogistica.getEndereco());
    }

    @Test
    void testSetEndereco() {
        Endereco novoEndereco =  new Endereco(
                6l,
                "06385820",
                "Rua Ipixuna",
                "154",
                "Vila Menk",
                "Carapicuiba",
                "SP",
                "São Paulo",
                "");
        notaFiscalLogistica.setEndereco(novoEndereco);
        assertEquals(novoEndereco, notaFiscalLogistica.getEndereco());
    }
}
