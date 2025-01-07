package com.gerenciador.frota.aplicacao.logistica.adapters.inbound;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso.GerenciarNotaFiscalCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class NotaFiscalLogiscaControllerTest {

    @InjectMocks
    private NotaFiscalLogiscaController notaFiscalLogiscaController;

    @Mock
    private GerenciarNotaFiscalCasoDeUso gerenciarNotaFiscalCasoDeUso;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void deveCadastrarNotaFiscalComSucesso() {
        // Arrange
        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("123456")
                .build();
        JpaNotaFiscalLogisticaEntity notaFiscal = JpaNotaFiscalLogisticaEntity.builder()
                .numeroNotaFisal("123456")
                .build();

        when(gerenciarNotaFiscalCasoDeUso.registrarNotaFiscal(request)).thenReturn(notaFiscal);

        // Act
        ResponseEntity<JpaNotaFiscalLogisticaEntity> response = notaFiscalLogiscaController.cadastrarNotaFiscal(request);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("123456", response.getBody().getNumeroNotaFisal());
        verify(gerenciarNotaFiscalCasoDeUso, times(1)).registrarNotaFiscal(request);
    }

    @Test
    void deveBuscarNotaFiscalComSucesso() {
        // Arrange
        Long codigoNotaFiscal = 1L;
        JpaNotaFiscalLogisticaEntity notaFiscal = JpaNotaFiscalLogisticaEntity.builder()
                .numeroNotaFisal("123456")
                .build();

        when(gerenciarNotaFiscalCasoDeUso.buscarNotaPeloCodigo(codigoNotaFiscal)).thenReturn(notaFiscal);

        // Act
        ResponseEntity<JpaNotaFiscalLogisticaEntity> response = notaFiscalLogiscaController.buscarNotaFiscalPorId(codigoNotaFiscal);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("123456", response.getBody().getNumeroNotaFisal());
        verify(gerenciarNotaFiscalCasoDeUso, times(1)).buscarNotaPeloCodigo(codigoNotaFiscal);
    }

    @Test
    void deveAtualizarNotaFiscalComSucesso() {
        // Arrange
        Long codigoNotaFiscal = 1L;
        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("789012")
                .build();
        RetornoServicoBase retorno = RetornoServicoBase.positivo("Nota Fiscal Atualizada");

        when(gerenciarNotaFiscalCasoDeUso.atualizarNotaFiscal(codigoNotaFiscal, request)).thenReturn(retorno);

        // Act
        ResponseEntity<RetornoServicoBase> response = notaFiscalLogiscaController.atualizarFiscalPorId(codigoNotaFiscal, request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Nota Fiscal Atualizada", response.getBody().getDescricao());
        verify(gerenciarNotaFiscalCasoDeUso, times(1)).atualizarNotaFiscal(codigoNotaFiscal, request);
    }

    @Test
    void deveDeletarNotaFiscalComSucesso() {
        // Arrange
        Long codigoNotaFiscal = 1L;
        RetornoServicoBase retorno = RetornoServicoBase.positivo("Nota Fiscal Deletada");

        when(gerenciarNotaFiscalCasoDeUso.deletarNotaFiscasl(codigoNotaFiscal)).thenReturn(retorno);

        // Act
        ResponseEntity<RetornoServicoBase> response = notaFiscalLogiscaController.deletarFiscalPorId(codigoNotaFiscal);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Nota Fiscal Deletada", response.getBody().getDescricao());
        verify(gerenciarNotaFiscalCasoDeUso, times(1)).deletarNotaFiscasl(codigoNotaFiscal);
    }
}
