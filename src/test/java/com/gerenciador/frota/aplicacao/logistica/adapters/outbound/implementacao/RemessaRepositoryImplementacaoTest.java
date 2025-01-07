package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaRemessaRepository;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class RemessaRepositoryImplementacaoTest {

    @InjectMocks
    private RemessaRepositoryImplementacao remessaRepositoryImplementacao;

    @Mock
    private JpaRemessaRepository jpaRemessaRepository;

    @Mock
    private Mappers mappers;

    private RemessaRequest remessaRequest;

    @BeforeEach
    void setUp() {
        openMocks(this);
        remessaRequest = RemessaRequest.builder()
                .cliente("Cliente A")
                .build();
    }

    @Test
    void deveSalvarRemessaComSucesso() {
        // Arrange
        RemessaResponse remessaResponse = new RemessaResponse(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(jpaRemessaRepository.save(any())).thenReturn(Mappers.fromRemessaToJpaRemessaEntity(new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA)));

        // Act
        RemessaResponse response = remessaRepositoryImplementacao.salvar(remessaRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Cliente A", response.getCliente());
        verify(jpaRemessaRepository, times(1)).save(any());
    }

    @Test
    void deveAtualizarRemessaComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        RetornoServicoBase retorno = RetornoServicoBase.positivo("Remessa atualizada com sucesso.");
        when(jpaRemessaRepository.save(any())).thenReturn(Mappers.fromRemessaToJpaRemessaEntity(remessa));

        // Act
        RetornoServicoBase response = remessaRepositoryImplementacao.salvar(Mappers.fromRemessaToJpaRemessaEntity(remessa));

        // Assert
        assertNotNull(response);
        assertEquals("Remessa atualizada com sucesso.", response.getDescricao());
        verify(jpaRemessaRepository, times(1)).save(any());
    }

    @Test
    void deveBuscarRemessaPorIdComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.of(Mappers.fromRemessaToJpaRemessaEntity(remessa)));

        // Act
        Remessa response = remessaRepositoryImplementacao.buscarRemessaPorId(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Cliente A", response.getCliente());
        verify(jpaRemessaRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarExceptionQuandoRemessaNaoEncontradaPorId() {
        // Arrange
        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            remessaRepositoryImplementacao.buscarRemessaPorId(1L);
        });
        assertEquals("Remessa com codigo fornecido não encontrado.", thrown.getMessage());
    }

    @Test
    void deveDeletarRemessaComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        RetornoServicoBase retorno = RetornoServicoBase.positivo("Remessa: 1 foi deletada com sucesso.");
        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.of(Mappers.fromRemessaToJpaRemessaEntity(remessa)));

        // Act
        RetornoServicoBase response = remessaRepositoryImplementacao.deletarRemessaPorId(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Remessa: 1 foi deletada com sucesso.", response.getDescricao());
        verify(jpaRemessaRepository, times(1)).delete(any());
    }

    @Test
    void deveRetornarErroAoTentarDeletarRemessaNaoEncontrada() {
        // Arrange
        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        RetornoServicoBase response = remessaRepositoryImplementacao.deletarRemessaPorId(1L);

        // Assert
        assertNotNull(response);
        assertEquals("Remessa: 1 não foi deletada.", response.getDescricao());
        verify(jpaRemessaRepository, times(0)).delete(any());
    }

    @Test
    void deveListarTodasRemessasComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(jpaRemessaRepository.findAll()).thenReturn(List.of(Mappers.fromRemessaToJpaRemessaEntity(remessa)));

        // Act
        List<Remessa> response = remessaRepositoryImplementacao.listarTodasRemessas();

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Cliente A", response.get(0).getCliente());
        verify(jpaRemessaRepository, times(1)).findAll();
    }

    @Test
    void deveListarRemessasPorStatusComSucesso() {
        // Arrange
        Remessa remessa = new Remessa(1L, "Cliente A", "2025-01-07", 10.0, 20.0, StatusRemessa.VAZIA);
        when(jpaRemessaRepository.findAllStatus("VAZIA")).thenReturn(List.of(Mappers.fromRemessaToJpaRemessaEntity(remessa)));

        // Act
        List<Remessa> response = remessaRepositoryImplementacao.listarTodasRemessasPorStatus(StatusRemessa.VAZIA);

        // Assert
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals("Cliente A", response.get(0).getCliente());
        verify(jpaRemessaRepository, times(1)).findAllStatus("VAZIA");
    }
}
