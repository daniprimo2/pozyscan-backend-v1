package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.NotaFiscalLogisticaRepositoryPort;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.MappersJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenciarNotaFiscalCasoDeUsoTest {

    @Mock
    private NotaFiscalLogisticaRepositoryPort fiscalLogisticaRepositoryPort;

    @InjectMocks
    private GerenciarNotaFiscalCasoDeUso gerenciarNotaFiscalCasoDeUso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarNotaFiscal() {
        // Arrange
        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("123456")
                .valorTotal(1500.00)
                .dataEmissao("2025-01-01")
                .build();

        NotaFiscalLogistica mockNotaFiscal = NotaFiscalLogistica.builder()
                .numeroNotaFisal("123456")
                .valorTotal(1500.00)
                .dataEmissao("2025-01-01")
                .build();

        JpaNotaFiscalLogisticaEntity mockEntity = JpaNotaFiscalLogisticaEntity.builder()
                .numeroNotaFisal("123456")
                .valorTotal(1500.00)
                .dataEmissao("2025-01-01")
                .build();

        mockStatic(MappersJpaEntity.class);
        when(fiscalLogisticaRepositoryPort.cadastrarNotaFiscal(request)).thenReturn(mockNotaFiscal);
        when(MappersJpaEntity.fromNotaFiscalToJpaNotaFiscalEntity(mockNotaFiscal)).thenReturn(mockEntity);

        // Act
        JpaNotaFiscalLogisticaEntity result = gerenciarNotaFiscalCasoDeUso.registrarNotaFiscal(request);

        // Assert
        assertNotNull(result);
        assertEquals("123456", result.getNumeroNotaFisal());
        assertEquals(1500.00, result.getValorTotal());
        verify(fiscalLogisticaRepositoryPort, times(1)).cadastrarNotaFiscal(request);
     }
//
//    @Test
//    void buscarNotaPeloCodigo() {
//        // Arrange
//        Long id = 1L;
//        JpaNotaFiscalLogisticaEntity mockEntity = JpaNotaFiscalLogisticaEntity.builder()
//                .numeroNotaFisal("123456")
//                .valorTotal(1500.00)
//                .dataEmissao("2025-01-01")
//                .build();
//
//        when(fiscalLogisticaRepositoryPort.buscarNotaFiscalPorId(id)).thenReturn(mockEntity);
//
//        // Act
//        JpaNotaFiscalLogisticaEntity result = gerenciarNotaFiscalCasoDeUso.buscarNotaPeloCodigo(id);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("123456", result.getNumeroNotaFisal());
//        assertEquals(1500.00, result.getValorTotal());
//        verify(fiscalLogisticaRepositoryPort, times(1)).buscarNotaFiscalPorId(id);
//    }
//
//    @Test
//    void atualizarNotaFiscal() {
//        // Arrange
//        Long id = 1L;
//        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
//                .numeroNotaFisal("654321")
//                .valorTotal(2000.00)
//                .dataEmissao("2025-02-01")
//                .build();
//
//        RetornoServicoBase mockRetorno = RetornoServicoBase.positivo("Atualizado com sucesso");
//        when(fiscalLogisticaRepositoryPort.atualizarNotaFiscal(id, request)).thenReturn(mockRetorno);
//
//        // Act
//        RetornoServicoBase result = gerenciarNotaFiscalCasoDeUso.atualizarNotaFiscal(id, request);
//
//        // Assert
//        assertNotNull(result);
//        assertTrue(result.isSuccesso());
//        assertEquals("Atualizado com sucesso", result.getMensagem());
//        verify(fiscalLogisticaRepositoryPort, times(1)).atualizarNotaFiscal(id, request);
//    }
//
//    @Test
//    void deletarNotaFiscasl() {
//        // Arrange
//        Long id = 1L;
//        RetornoServicoBase mockRetorno = RetornoServicoBase.positivo("Deletado com sucesso");
//        when(fiscalLogisticaRepositoryPort.deletarNotaFiscal(id)).thenReturn(mockRetorno);
//
//        // Act
//        RetornoServicoBase result = gerenciarNotaFiscalCasoDeUso.deletarNotaFiscasl(id);
//
//        // Assert
//        assertNotNull(result);
//        assertTrue(result.isSuccesso());
//        assertEquals("Deletado com sucesso", result.getMensagem());
//        verify(fiscalLogisticaRepositoryPort, times(1)).deletarNotaFiscal(id);
//    }
}
