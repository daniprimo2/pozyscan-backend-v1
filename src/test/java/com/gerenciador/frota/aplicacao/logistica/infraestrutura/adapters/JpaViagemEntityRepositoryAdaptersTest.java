package com.gerenciador.frota.aplicacao.logistica.infraestrutura.adapters;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.ViagemRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaViagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaViagemEntityRepositoryAdaptersTest {

    @Mock
    private JpaViagemRepository jpaViagemRepository;

    @InjectMocks
    private ViagemRepositoryImplementacao viagemRepositoryImplementacao;

    private ViagemRequest viagemRequest;
    private JpaViagemEntity jpaViagemEntity;

    @BeforeEach
    void setUp() {
        viagemRequest = new ViagemRequest(
                LocalDate.of(2024, 12, 15),
                TipoViagem.NAO_REMUNERADA
        );

        jpaViagemEntity = JpaViagemEntity.builder()
                .id(1L)
                .dataCriacao("10/12/2024")
                .dataProgramadaViagem("15/12/2024")
                .dataRealizadoViagem(null)
                .volumeTotal(0.0)
                .pesoTotal(0.0)
                .totalKilometragem(0.0)
                .totalRemessa(0.0)
                .tipoViagem(TipoViagem.NAO_REMUNERADA)
                .build();
    }

    @Test
    void deveSalvarViagemComSucesso() {
        // Arrange
        when(jpaViagemRepository.save(any(JpaViagemEntity.class))).thenReturn(jpaViagemEntity);

        // Act
        Viagem jpaViagemEntitySalva = viagemRepositoryImplementacao.salvar(viagemRequest);

        // Assert
        assertNotNull(jpaViagemEntitySalva);
        assertEquals("15/12/2024", jpaViagemEntitySalva.getDataProgramadaViagem());
        assertEquals(TipoViagem.NAO_REMUNERADA, jpaViagemEntitySalva.getTipoViagem());
        verify(jpaViagemRepository, times(1)).save(any(JpaViagemEntity.class));
    }

    @Test
    void deveLancarExcecaoAoSalvarViagemComErro() {
        // Arrange
        when(jpaViagemRepository.save(any(JpaViagemEntity.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> viagemRepositoryImplementacao.salvar(viagemRequest));
        assertEquals("Erro ao salvar", exception.getMessage());
        verify(jpaViagemRepository, times(1)).save(any(JpaViagemEntity.class));
    }

    @Test
    void deveMontarViagemCorretamente() {
        // Act
        JpaViagemEntity jpaViagemEntityMontada = viagemRequest.construirViagem();

        // Assert
        assertNotNull(jpaViagemEntityMontada);
        assertEquals("15/12/2024", jpaViagemEntityMontada.getDataProgramadaViagem());
        assertEquals(TipoViagem.NAO_REMUNERADA, jpaViagemEntityMontada.getTipoViagem());
        assertEquals(0.0, jpaViagemEntityMontada.getVolumeTotal());
        assertEquals(0.0, jpaViagemEntityMontada.getPesoTotal());
    }

    @Test
    void deveListarTodasAsViagens() {
        // Arrange
        when(jpaViagemRepository.findAll()).thenReturn(List.of(jpaViagemEntity));

        // Act
        List<Viagem> viagens = viagemRepositoryImplementacao.listar();

        // Assert
        assertNotNull(viagens);
        assertEquals(1, viagens.size());
        assertEquals(jpaViagemEntity.getId(), viagens.get(0).getId());
        verify(jpaViagemRepository, times(1)).findAll();
    }

    @Test
    void deveListarViagensComFiltro() {
        // Arrange
        FiltroViagemRequest filtroRequest = new FiltroViagemRequest("15/12/2024", TipoViagem.NAO_REMUNERADA);
        when(jpaViagemRepository.findAllFiltro(filtroRequest.getDataViagemProgramada(), filtroRequest.getTipoViagem()))
                .thenReturn(List.of(jpaViagemEntity));

        // Act
        List<Viagem> viagens = viagemRepositoryImplementacao.listar(filtroRequest);

        // Assert
        assertNotNull(viagens);
        assertEquals(1, viagens.size());
        assertEquals(jpaViagemEntity.getTipoViagem(), viagens.get(0).getTipoViagem());
        verify(jpaViagemRepository, times(1))
                .findAllFiltro(filtroRequest.getDataViagemProgramada(), filtroRequest.getTipoViagem());
    }

    @Test
    void deveBuscarViagemPorIdComSucesso() {
        // Arrange
        when(jpaViagemRepository.findById(1L)).thenReturn(Optional.of(jpaViagemEntity));

        // Act
        Viagem jpaViagemEntityEncontrada = viagemRepositoryImplementacao.buscarViagemPorId(1L);

        // Assert
        assertNotNull(jpaViagemEntityEncontrada);
        assertEquals(jpaViagemEntity.getId(), jpaViagemEntityEncontrada.getId());
        verify(jpaViagemRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoBuscarViagemInexistente() {
        // Arrange
        when(jpaViagemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> viagemRepositoryImplementacao.buscarViagemPorId(1L));
        assertEquals("Viagem não encontrada", exception.getMessage());
        verify(jpaViagemRepository, times(1)).findById(1L);
    }

    @Test
    @Disabled("Nao esta sendo possivel criar este teste")
    void deveDeletarViagemComSucesso() {
        // Arrange
        when(jpaViagemRepository.findById(1L)).thenReturn(Optional.of(jpaViagemEntity));
        doNothing().when(jpaViagemRepository).delete(any(JpaViagemEntity.class));

        // Act
        RetornoServicoBase retorno = viagemRepositoryImplementacao.deletarViagemPorId(1L);

        // Assert
        assertNotNull(retorno);
        assertTrue(retorno.getFuncionou());
        assertEquals("Viagem deletada com sucesso.", retorno.getDescricao());
        verify(jpaViagemRepository, times(1)).delete(jpaViagemEntity);
    }

    @Test
    void deveRetornarErroAoDeletarViagemInexistente() {
        // Arrange
        when(jpaViagemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        RetornoServicoBase retorno = viagemRepositoryImplementacao.deletarViagemPorId(1L);

        // Assert
        assertNotNull(retorno);
        assertFalse(retorno.getFuncionou());
        assertEquals("Não foi possivel deletar a viagem.", retorno.getDescricao());
        verify(jpaViagemRepository, times(0)).delete(any(JpaViagemEntity.class));
    }


}
