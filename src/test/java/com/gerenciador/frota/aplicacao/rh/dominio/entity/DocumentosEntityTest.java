package com.gerenciador.frota.aplicacao.rh.dominio.entity;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.DocumentosResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentosEntityTest {

    private DocumentosEntity documentosEntity;

    @BeforeEach
    void setUp() {
        documentosEntity = DocumentosEntity.builder()
                .id(1L)
                .numeroDocumento("123456789")
                .dataExpedicao("2022-01-01")
                .dataValidade("2032-01-01")
                .orgaoEmissor("SSP")
                .arquivoBase64("base64encodedstring")
                .tipoDocumento(TipoDocumento.CNH)
                .colaboradorEntity(null)
                .build();
    }

    @Test
    void deveCriarDocumentoComSucesso() {
        assertNotNull(documentosEntity);
        assertEquals(1L, documentosEntity.getId());
        assertEquals("123456789", documentosEntity.getNumeroDocumento());
        assertEquals("2022-01-01", documentosEntity.getDataExpedicao());
        assertEquals("2032-01-01", documentosEntity.getDataValidade());
        assertEquals("SSP", documentosEntity.getOrgaoEmissor());
        assertEquals("base64encodedstring", documentosEntity.getArquivoBase64());
        assertEquals(TipoDocumento.CNH, documentosEntity.getTipoDocumento());
        assertNull(documentosEntity.getColaboradorEntity());
    }

    @Test
    void deveAtualizarDocumentoComSucesso() {
        DocumentosEntity novoDocumento = DocumentosEntity.builder()
                .numeroDocumento("987654321")
                .dataExpedicao("2023-01-01")
                .dataValidade("2033-01-01")
                .orgaoEmissor("SSP-SP")
                .arquivoBase64("newbase64encodedstring")
                .tipoDocumento(TipoDocumento.RG)
                .colaboradorEntity(null)
                .build();

        documentosEntity.atualizarDocumento(novoDocumento);

        assertEquals("987654321", documentosEntity.getNumeroDocumento());
        assertEquals("2023-01-01", documentosEntity.getDataExpedicao());
        assertEquals("2033-01-01", documentosEntity.getDataValidade());
        assertEquals("SSP-SP", documentosEntity.getOrgaoEmissor());
        assertEquals("newbase64encodedstring", documentosEntity.getArquivoBase64());
        assertEquals(TipoDocumento.RG, documentosEntity.getTipoDocumento());
    }

    @Test
    void deveConstruirDocumentoResponseComSucesso() {
        DocumentosResponse response = documentosEntity.construirDocumentoResponse();

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("123456789", response.getNumeroDocumento());
        assertEquals("2022-01-01", response.getDataExpedicao());
        assertEquals("2032-01-01", response.getDataValidade());
        assertEquals("SSP", response.getOrgaoEmissor());
        assertEquals("base64encodedstring", response.getArquivoBase64());
        assertEquals(TipoDocumento.CNH, response.getTipoDocumento());
    }

    @Test
    void deveCriarDocumentoAPartirDeDocumentoResponse() {
        DocumentosResponse response = DocumentosResponse.builder()
                .id(2L)
                .numeroDocumento("123456789")
                .dataExpedicao("2022-01-01")
                .dataValidade("2032-01-01")
                .orgaoEmissor("SSP")
                .arquivoBase64("base64encodedstring")
                .tipoDocumento(TipoDocumento.CNH)
                .build();

        documentosEntity.fromDocumento(response);

        assertEquals(2L, documentosEntity.getId());
        assertEquals("123456789", documentosEntity.getNumeroDocumento());
        assertEquals("2022-01-01", documentosEntity.getDataExpedicao());
        assertEquals("2032-01-01", documentosEntity.getDataValidade());
        assertEquals("SSP", documentosEntity.getOrgaoEmissor());
        assertEquals("base64encodedstring", documentosEntity.getArquivoBase64());
        assertEquals(TipoDocumento.CNH, documentosEntity.getTipoDocumento());
    }
}
