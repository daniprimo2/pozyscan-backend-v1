package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaNotaFiscalLogisticaRepository;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotaFiscalLogisticaImplementacaoTest {

    @Mock
    private JpaNotaFiscalLogisticaRepository notaFiscalLogisticaRepository;

    @Mock
    private ViaCepSerive viaCepSerive;

    @InjectMocks
    private NotaFiscalLogisticaImplementacao notaFiscalLogisticaImplementacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarNotaFiscal() {
        // Arrange
        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .build();

        JpaNotaFiscalLogisticaEntity savedEntity = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(1L)
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .build();

        when(notaFiscalLogisticaRepository.save(any(JpaNotaFiscalLogisticaEntity.class)))
                .thenReturn(savedEntity);

        // Act
        NotaFiscalLogistica result = notaFiscalLogisticaImplementacao.cadastrarNotaFiscal(request);

        // Assert
        assertNotNull(result);
        assertEquals("123456", result.getNumeroNotaFisal());
        verify(notaFiscalLogisticaRepository, times(1)).save(any(JpaNotaFiscalLogisticaEntity.class));
    }

    @Test
    void buscarTodasNotasFiscais() {
        // Arrange
        when(notaFiscalLogisticaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        var result = notaFiscalLogisticaImplementacao.buscarTodasNotasFiscais();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(notaFiscalLogisticaRepository, times(1)).findAll();
    }

    @Test
    void buscarNotasFiscaisPorCodigo() {
        // Arrange
        JpaNotaFiscalLogisticaEntity entity = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(1L)
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .build();

        when(notaFiscalLogisticaRepository.findById(1L)).thenReturn(Optional.of(entity));

        // Act
        NotaFiscalLogistica result = notaFiscalLogisticaImplementacao.buscarNotasFiscaisPorCodigo(1L);

        // Assert
        assertNotNull(result);
        assertEquals("123456", result.getNumeroNotaFisal());
        verify(notaFiscalLogisticaRepository, times(1)).findById(1L);
    }

    @Test
    void buscarNotasFiscaisPorCodigoJpa() {
        // Arrange
        JpaNotaFiscalLogisticaEntity entity = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(1L)
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .build();

        when(notaFiscalLogisticaRepository.findById(1L)).thenReturn(Optional.of(entity));

        // Act
        JpaNotaFiscalLogisticaEntity result = notaFiscalLogisticaImplementacao.buscarNotasFiscaisPorCodigoJpa(1L);

        // Assert
        assertNotNull(result);
        assertEquals("123456", result.getNumeroNotaFisal());
        verify(notaFiscalLogisticaRepository, times(1)).findById(1L);
    }

    @Test
    void atualziarNotaFiscal() {
        // Arrange
        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("123456")
                .valorTotal(1500.0)
                .dataEmissao("2025-01-01")
                .build();

        JpaNotaFiscalLogisticaEntity existingEntity = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(1L)
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .build();

        when(notaFiscalLogisticaRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
        when(notaFiscalLogisticaRepository.save(any(JpaNotaFiscalLogisticaEntity.class)))
                .thenReturn(existingEntity);

        // Act
        RetornoServicoBase result = notaFiscalLogisticaImplementacao.atualziarNotaFiscal(1L, request);

        // Assert
        assertTrue(result.getFuncionou());
        verify(notaFiscalLogisticaRepository, times(1)).findById(1L);
        verify(notaFiscalLogisticaRepository, times(1)).save(any(JpaNotaFiscalLogisticaEntity.class));
    }

    @Test
    void deletarNotaFiscal() {
        // Arrange
        JpaNotaFiscalLogisticaEntity entity = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(1L)
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .build();

        when(notaFiscalLogisticaRepository.findById(1L)).thenReturn(Optional.of(entity));

        // Act
        RetornoServicoBase result = notaFiscalLogisticaImplementacao.deletarNotaFiscal(1L);

        // Assert
        assertTrue(result.getFuncionou());
        verify(notaFiscalLogisticaRepository, times(1)).findById(1L);
        verify(notaFiscalLogisticaRepository, times(1)).delete(entity);
    }
}
