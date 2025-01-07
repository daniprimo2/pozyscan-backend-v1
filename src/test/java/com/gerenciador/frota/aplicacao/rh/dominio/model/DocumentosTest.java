package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentosTest {

    private Documentos documento;
    private Colaborador colaborador;

    @BeforeEach
    void setUp() {
        colaborador = new Colaborador();
        documento = new Documentos(
                1L,
                "123.456.789-00",
                "01/01/2022",
                "01/01/2032",
                "SSP-SP",
                "arquivoBase64Exemplo",
                TipoDocumento.CPF,
                colaborador
        );
    }

    @Test
    void deveCriarDocumentoComSucesso() {
        assertNotNull(documento);
        assertEquals(1L, documento.getId());
        assertEquals("123.456.789-00", documento.getNumeroDocumento());
        assertEquals("01/01/2022", documento.getDataExpedicao());
        assertEquals("01/01/2032", documento.getDataValidade());
        assertEquals("SSP-SP", documento.getOrgaoEmissor());
        assertEquals("arquivoBase64Exemplo", documento.getArquivoBase64());
        assertEquals(TipoDocumento.CPF, documento.getTipoDocumento());
        assertEquals(colaborador, documento.getColaborador());
    }

    @Test
    void deveAtualizarNumeroDocumentoComSucesso() {
        documento.setNumeroDocumento("987.654.321-00");
        assertEquals("987.654.321-00", documento.getNumeroDocumento());
    }

    @Test
    void deveAtualizarDataExpedicaoComSucesso() {
        documento.setDataExpedicao("01/01/2023");
        assertEquals("01/01/2023", documento.getDataExpedicao());
    }

    @Test
    void deveAtualizarDataValidadeComSucesso() {
        documento.setDataValidade("01/01/2040");
        assertEquals("01/01/2040", documento.getDataValidade());
    }

    @Test
    void deveAtualizarOrgaoEmissorComSucesso() {
        documento.setOrgaoEmissor("SSP-RJ");
        assertEquals("SSP-RJ", documento.getOrgaoEmissor());
    }

    @Test
    void deveAtualizarArquivoBase64ComSucesso() {
        documento.setArquivoBase64("novoArquivoBase64");
        assertEquals("novoArquivoBase64", documento.getArquivoBase64());
    }

    @Test
    void deveAtualizarTipoDocumentoComSucesso() {
        documento.setTipoDocumento(TipoDocumento.RG);
        assertEquals(TipoDocumento.RG, documento.getTipoDocumento());
    }

    @Test
    void deveAtualizarColaboradorComSucesso() {
        Colaborador novoColaborador = new Colaborador();
        documento.setColaborador(novoColaborador);
        assertEquals(novoColaborador, documento.getColaborador());
    }

    @Test
    void deveCriarDocumentoUsandoConstrutorVazio() {
        Documentos novoDocumento = new Documentos();
        assertNull(novoDocumento.getId());
        assertNull(novoDocumento.getNumeroDocumento());
        assertNull(novoDocumento.getDataExpedicao());
        assertNull(novoDocumento.getDataValidade());
        assertNull(novoDocumento.getOrgaoEmissor());
        assertNull(novoDocumento.getArquivoBase64());
        assertNull(novoDocumento.getTipoDocumento());
        assertNull(novoDocumento.getColaborador());
    }
}
