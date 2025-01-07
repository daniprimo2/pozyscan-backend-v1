package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GerenciarNotaFiscalCasoDeUsoTest {

    private GerenciarNotaFiscalCasoDeUso gerenciarNotaFiscalCasoDeUso;

    @BeforeEach
    void setUp() {
        gerenciarNotaFiscalCasoDeUso = new GerenciarNotaFiscalCasoDeUso();
    }

    @Test
    void deveRegistrarNotaFiscalComSucesso() {
        // Arrange
        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("123456")
                .build();

        // Act
        JpaNotaFiscalLogisticaEntity notaFiscal = gerenciarNotaFiscalCasoDeUso.registrarNotaFiscal(request);

        // Assert
        assertNotNull(notaFiscal);
        assertEquals("123456", notaFiscal.getNumeroNotaFisal());
    }

    @Test
    void deveBuscarNotaFiscalPeloCodigoComSucesso() {
        // Act
        JpaNotaFiscalLogisticaEntity notaFiscal = gerenciarNotaFiscalCasoDeUso.buscarNotaPeloCodigo(1L);

        // Assert
        assertNotNull(notaFiscal);
        assertNull(notaFiscal.getNumeroNotaFisal()); // Já que não foi configurado um valor
    }

    @Test
    void deveAtualizarNotaFiscalComSucesso() {
        // Arrange
        Long codigoNotaFiscal = 1L;
        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("789012")
                .build();

        // Act
        RetornoServicoBase retorno = gerenciarNotaFiscalCasoDeUso.atualizarNotaFiscal(codigoNotaFiscal, request);

        // Assert
        assertNotNull(retorno);
        assertTrue(retorno.getFuncionou());
        assertEquals(1, retorno.getCodErro());
        assertEquals("", retorno.getDescricao());
    }

    @Test
    void deveDeletarNotaFiscalComSucesso() {
        // Arrange
        Long codigoNotaFiscal = 1L;

        // Act
        RetornoServicoBase retorno = gerenciarNotaFiscalCasoDeUso.deletarNotaFiscasl(codigoNotaFiscal);

        // Assert
        assertNotNull(retorno);
        assertTrue(retorno.getFuncionou());
        assertEquals(1, retorno.getCodErro());
        assertEquals("", retorno.getDescricao());
    }
}
