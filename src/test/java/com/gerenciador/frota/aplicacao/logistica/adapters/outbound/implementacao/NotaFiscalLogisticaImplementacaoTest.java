package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.dto.response.EnderecoResponse;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.integracoes.repository.EnderecoRepository;
import com.gerenciador.frota.aplicacao.integracoes.service.EnderecoService;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaNotaFiscalLogisticaRepository;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.EnderecoRequest;
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
    private EnderecoRepository enderecoRepository;

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
        EnderecoRequest enderecoRequest = new EnderecoRequest(
                "12345678",
                "Rua Nova",
                "100",
                "Centro",
                "São Paulo",
                "SP",
                "São Paulo",
                "Apt 101");

        Endereco endereco = new Endereco(
                6L,
                "12345678",
                "Rua Nova",
                "100",
                "Centro",
                "São Paulo",
                "SP",
                "São Paulo",
                "Apt 101");

        EnderecoResponse enderecoResponse = new EnderecoResponse(
                "12345678",
                "Rua Nova",
                "Centro",
                "São Paulo",
                "SP",
                "São Paulo",
                "Apt 101");

        NotaFiscalLogisticaRequest request = NotaFiscalLogisticaRequest.builder()
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .enderecoRequest(enderecoRequest)
                .build();

        JpaNotaFiscalLogisticaEntity savedEntity = JpaNotaFiscalLogisticaEntity.builder()
                .codigoNotaFiscal(1L)
                .numeroNotaFisal("123456")
                .valorTotal(1000.0)
                .dataEmissao("2025-01-01")
                .endereco(endereco)
                .build();

        when(viaCepSerive.buscarEnderecoPorCep("12345678"))
                .thenReturn(enderecoResponse);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(notaFiscalLogisticaRepository.save(any(JpaNotaFiscalLogisticaEntity.class))).thenReturn(savedEntity);

        // Act
        NotaFiscalLogistica result = notaFiscalLogisticaImplementacao.cadastrarNotaFiscal(request);

        // Assert
        assertNotNull(result);
        assertEquals("123456", result.getNumeroNotaFisal());
        assertEquals(1000.0, result.getValorTotal());
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
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
