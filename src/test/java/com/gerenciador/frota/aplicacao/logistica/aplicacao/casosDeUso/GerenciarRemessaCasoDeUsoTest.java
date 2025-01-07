package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.RemessaRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso.GerenciarRemessaCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class GerenciarRemessaCasoDeUsoTest {

    @InjectMocks
    private GerenciarRemessaCasoDeUso gerenciarRemessaCasoDeUso;

    @Mock
    private RemessaRepositoryImplementacao remessaRepositoryImplementacao;

    private RemessaRequest remessaRequest;
    private RemessaResponse remessaResponse;

    @BeforeEach
    void setUp() {
        openMocks(this);
        remessaRequest = RemessaRequest.builder()
                .cliente("Cliente A")
                .build();

        remessaResponse = new RemessaResponse();
    }

    @Test
    void deveSalvarRemessaComSucesso() {
        // Arrange
        RemessaResponse remessaResponse = new RemessaResponse(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(remessaRepositoryImplementacao.salvar(any(RemessaRequest.class))).thenReturn(remessaResponse);

        // Act
        RemessaResponse response = gerenciarRemessaCasoDeUso.cadastrarNovaRemessa(remessaRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Cliente A", response.getCliente());
        verify(remessaRepositoryImplementacao, times(1)).salvar(any(RemessaRequest.class));
    }

    @Test
    @Disabled("Voltar depois")
    void deveAtualizarRemessaComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        RetornoServicoBase retorno = RetornoServicoBase.positivo("Remessa atualizada com sucesso.");
        when(remessaRepositoryImplementacao.salvar(remessaRequest));

        // Act
        RetornoServicoBase response = gerenciarRemessaCasoDeUso.atualziarRemessaPorId(1l, remessaRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Remessa atualizada com sucesso.", response.getDescricao());
        verify(remessaRepositoryImplementacao, times(1));
    }

    @Test
    void deveBuscarRemessaPorIdComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(remessaRepositoryImplementacao.buscarRemessaPorId(1L)).thenReturn(remessa);

        // Act
        Remessa response = gerenciarRemessaCasoDeUso.buscarRemessaPorId(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Cliente A", response.getCliente());
        verify(remessaRepositoryImplementacao, times(1)).buscarRemessaPorId(1L);
    }

    @Test
    void deveRetornarExceptionQuandoRemessaNaoEncontradaPorId() {
        // Arrange
        when(remessaRepositoryImplementacao.buscarRemessaPorId(1L)).thenThrow(new RuntimeException("Remessa com codigo fornecido não encontrado."));

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            gerenciarRemessaCasoDeUso.buscarRemessaPorId(1L);
        });
        assertEquals("Remessa com codigo fornecido não encontrado.", thrown.getMessage());
    }

    @Test
    void deveDeletarRemessaComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        RetornoServicoBase retorno = RetornoServicoBase.positivo("Remessa: 1 foi deletada com sucesso.");
        when(remessaRepositoryImplementacao.deletarRemessaPorId(1L)).thenReturn(retorno);

        // Act
        RetornoServicoBase response = gerenciarRemessaCasoDeUso.deletarRemessa(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Remessa: 1 foi deletada com sucesso.", response.getDescricao());
        verify(remessaRepositoryImplementacao, times(1)).deletarRemessaPorId(1L);
    }

    @Test
    void deveListarTodasRemessasComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(remessaRepositoryImplementacao.listarTodasRemessas()).thenReturn(List.of(remessa));

        // Act
        List<Remessa> response = gerenciarRemessaCasoDeUso.listarTodasRemessas();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Cliente A", response.get(0).getCliente());
        verify(remessaRepositoryImplementacao, times(1)).listarTodasRemessas();
    }

    @Test
    void deveListarRemessasPorStatusComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(remessaRepositoryImplementacao.listarTodasRemessasPorStatus(StatusRemessa.VAZIA)).thenReturn(List.of(remessa));

        // Act
        List<Remessa> response = gerenciarRemessaCasoDeUso.listarTodasRemessasPorTipo(StatusRemessa.VAZIA);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Cliente A", response.get(0).getCliente());
        verify(remessaRepositoryImplementacao, times(1)).listarTodasRemessasPorStatus(StatusRemessa.VAZIA);
    }
}
