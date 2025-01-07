package com.gerenciador.frota.aplicacao.logistica.infraestrutura.adapters;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums.TipoViagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.ViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.persistencia.ViagemRepository;
import org.junit.jupiter.api.BeforeEach;
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
class ViagemRepositoryAdaptersTest {

    @Mock
    private ViagemRepository viagemRepository;

    @InjectMocks
    private ViagemRepositoryAdapters viagemRepositoryAdapters;

    private ViagemRequest viagemRequest;
    private Viagem viagem;

    @BeforeEach
    void setUp() {
        viagemRequest = new ViagemRequest(
                LocalDate.of(2024, 12, 15),
                TipoViagem.NAO_REMUNERADA
        );

        viagem = Viagem.builder()
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
        when(viagemRepository.save(any(Viagem.class))).thenReturn(viagem);

        // Act
        Viagem viagemSalva = viagemRepositoryAdapters.salvar(viagemRequest);

        // Assert
        assertNotNull(viagemSalva);
        assertEquals("15/12/2024", viagemSalva.getDataProgramadaViagem());
        assertEquals(TipoViagem.NAO_REMUNERADA, viagemSalva.getTipoViagem());
        verify(viagemRepository, times(1)).save(any(Viagem.class));
    }

    @Test
    void deveLancarExcecaoAoSalvarViagemComErro() {
        // Arrange
        when(viagemRepository.save(any(Viagem.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> viagemRepositoryAdapters.salvar(viagemRequest));
        assertEquals("Erro ao salvar", exception.getMessage());
        verify(viagemRepository, times(1)).save(any(Viagem.class));
    }

    @Test
    void deveMontarViagemCorretamente() {
        // Act
        Viagem viagemMontada = viagemRequest.construirViagem();

        // Assert
        assertNotNull(viagemMontada);
        assertEquals("15/12/2024", viagemMontada.getDataProgramadaViagem());
        assertEquals(TipoViagem.NAO_REMUNERADA, viagemMontada.getTipoViagem());
        assertEquals(0.0, viagemMontada.getVolumeTotal());
        assertEquals(0.0, viagemMontada.getPesoTotal());
    }

    @Test
    void deveListarTodasAsViagens() {
        // Arrange
        when(viagemRepository.findAll()).thenReturn(List.of(viagem));

        // Act
        List<Viagem> viagens = viagemRepositoryAdapters.listar();

        // Assert
        assertNotNull(viagens);
        assertEquals(1, viagens.size());
        assertEquals(viagem.getId(), viagens.get(0).getId());
        verify(viagemRepository, times(1)).findAll();
    }

    @Test
    void deveListarViagensComFiltro() {
        // Arrange
        FiltroViagemRequest filtroRequest = new FiltroViagemRequest("15/12/2024", TipoViagem.NAO_REMUNERADA);
        when(viagemRepository.findAllFiltro(filtroRequest.getDataViagemProgramada(), filtroRequest.getTipoViagem()))
                .thenReturn(List.of(viagem));

        // Act
        List<Viagem> viagens = viagemRepositoryAdapters.listar(filtroRequest);

        // Assert
        assertNotNull(viagens);
        assertEquals(1, viagens.size());
        assertEquals(viagem.getTipoViagem(), viagens.get(0).getTipoViagem());
        verify(viagemRepository, times(1))
                .findAllFiltro(filtroRequest.getDataViagemProgramada(), filtroRequest.getTipoViagem());
    }

    @Test
    void deveBuscarViagemPorIdComSucesso() {
        // Arrange
        when(viagemRepository.findById(1L)).thenReturn(Optional.of(viagem));

        // Act
        Viagem viagemEncontrada = viagemRepositoryAdapters.buscarViagemPorId(1L);

        // Assert
        assertNotNull(viagemEncontrada);
        assertEquals(viagem.getId(), viagemEncontrada.getId());
        verify(viagemRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoBuscarViagemInexistente() {
        // Arrange
        when(viagemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> viagemRepositoryAdapters.buscarViagemPorId(1L));
        assertEquals("Viagem não encontrada", exception.getMessage());
        verify(viagemRepository, times(1)).findById(1L);
    }

    @Test
    void deveDeletarViagemComSucesso() {
        // Arrange
        when(viagemRepository.findById(1L)).thenReturn(Optional.of(viagem));
        doNothing().when(viagemRepository).delete(any(Viagem.class));

        // Act
        RetornoServicoBase retorno = viagemRepositoryAdapters.deletarViagemPorId(1L);

        // Assert
        assertNotNull(retorno);
        assertTrue(retorno.getFuncionou());
        assertEquals("Viagem deletada com sucesso.", retorno.getDescricao());
        verify(viagemRepository, times(1)).delete(viagem);
    }

    @Test
    void deveRetornarErroAoDeletarViagemInexistente() {
        // Arrange
        when(viagemRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        RetornoServicoBase retorno = viagemRepositoryAdapters.deletarViagemPorId(1L);

        // Assert
        assertNotNull(retorno);
        assertFalse(retorno.getFuncionou());
        assertEquals("Não foi possivel deletar a viagem.", retorno.getDescricao());
        verify(viagemRepository, times(0)).delete(any(Viagem.class));
    }


}
