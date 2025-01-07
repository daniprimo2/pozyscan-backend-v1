package com.gerenciador.frota.aplicacao.rh.infraestrutura.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao.CargoRepositoryAdapter;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.CargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.FiltroCargoRequest;
import com.gerenciador.frota.aplicacao.rh.dominio.exceptions.CargoNaoEncontradoException;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.CargoRepository;
import com.gerenciador.frota.aplicacao.rh.dominio.model.Cargo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CargoEntityRepositoryAdapterTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoRepositoryAdapter cargoRepositoryAdapter;

    private CargoEntity cargoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cargoEntity = CargoEntity.builder()
                .id(1L)
                .nomeCargo("GERENTE")
                .descricaoCargo("Gerente de Projetos")
                .build();
    }

    @Test
    void deveSalvarCargoComSucesso() {
        when(cargoRepository.save(cargoEntity)).thenReturn(cargoEntity);

        CargoEntity resultado = cargoRepositoryAdapter.salva(cargoEntity);

        assertNotNull(resultado);
        assertEquals(cargoEntity.getNomeCargo(), resultado.getNomeCargo());
        verify(cargoRepository, times(1)).save(cargoEntity);
    }

    @Test
    void deveListarTodosOsCargos() {
        when(cargoRepository.findAll()).thenReturn(Collections.singletonList(cargoEntity));

        List<CargoEntity> resultado = cargoRepositoryAdapter.listarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(cargoEntity.getNomeCargo(), resultado.get(0).getNomeCargo());
        verify(cargoRepository, times(1)).findAll();
    }

    @Test
    void deveListarCargosPorFiltro() {
        FiltroCargoRequest filtro = new FiltroCargoRequest();
        filtro.setCargo("GERENTE");
        filtro.setDescricaoCargo("Gerente de Projetos");

        when(cargoRepository.findCargoPorCargoPorDescCargo(filtro.getCargo(), filtro.getDescricaoCargo()))
                .thenReturn(Collections.singletonList(cargoEntity));

        List<CargoEntity> resultado = cargoRepositoryAdapter.listarPorFiltro(filtro);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(cargoEntity.getNomeCargo(), resultado.get(0).getNomeCargo());
        verify(cargoRepository, times(1)).findCargoPorCargoPorDescCargo(filtro.getCargo(), filtro.getDescricaoCargo());
    }

    @Test
    void deveAtualizarCargoComSucesso() {
        CargoRequest cargoRequest = CargoRequest.builder().nomeCargo("ATUALIZADO").descricaoCargo("Atualizado").build();
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargoEntity));
        when(cargoRepository.save(any())).thenReturn(cargoEntity);

        RetornoServicoBase resultado = cargoRepositoryAdapter.atualizar(1L, cargoRequest);

        assertTrue(resultado.getFuncionou());
        assertEquals("Cargo 1 foi alterado com sucesso.", resultado.getDescricao());
        verify(cargoRepository, times(1)).save(any(CargoEntity.class));
    }
    @Test
    void deveFalharAtualizarCargoComSucesso() {
        CargoRequest cargoRequest = CargoRequest.builder().nomeCargo("ATUALIZADO").descricaoCargo("Atualizado").build();
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargoEntity));
        when(cargoRepository.save(any())).thenReturn(new RuntimeException("Erro no banco de dados"));

        RetornoServicoBase resultado = cargoRepositoryAdapter.atualizar(1L, cargoRequest);

        assertFalse(resultado.getFuncionou());
        assertEquals("Não foi possivel alterar este cadastro de cargo", resultado.getDescricao());
        verify(cargoRepository, times(1)).save(any(CargoEntity.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarCargoNaoEncontrado() {
        CargoRequest cargoRequest = CargoRequest.builder().nomeCargo("ATUALIZADO").descricaoCargo("Atualizado").build();
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CargoNaoEncontradoException.class, () -> cargoRepositoryAdapter.atualizar(1L, cargoRequest));
        verify(cargoRepository, times(0)).save(any(CargoEntity.class));
    }

    @Test
    void deveDesativarCargoComSucesso() {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargoEntity));

        RetornoServicoBase resultado = cargoRepositoryAdapter.desativar(1L);

        assertTrue(resultado.getFuncionou());
        assertEquals("Cargo1 foi deletado com sucesso.", resultado.getDescricao());
        verify(cargoRepository, times(1)).delete(cargoEntity);
    }
    @Test
    void deveFalharAoDesativarCargoNaoExistente() {
        Long codigoCargo = 1L;
        when(cargoRepository.findById(codigoCargo)).thenReturn(Optional.empty());

        CargoNaoEncontradoException exception = assertThrows(
                CargoNaoEncontradoException.class,
                () -> cargoRepositoryAdapter.desativar(codigoCargo)
        );

        assertEquals("Cargo não foi encontrado.", exception.getMessage());
        verify(cargoRepository, never()).delete(any(CargoEntity.class));
    }

    @Test
    void deveLancarExcecaoAoDesativarCargoNaoEncontrado() {
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CargoNaoEncontradoException.class, () -> cargoRepositoryAdapter.desativar(1L));
        verify(cargoRepository, times(0)).delete(any(CargoEntity.class));
    }

    @Test
    void deveBuscarCargoPorIdComSucesso() {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargoEntity));

        CargoEntity resultado = cargoRepositoryAdapter.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(cargoEntity.getId(), resultado.getId());
        verify(cargoRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoBuscarCargoPorIdNaoEncontrado() {
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CargoNaoEncontradoException.class, () -> cargoRepositoryAdapter.buscarPorId(1L));
        verify(cargoRepository, times(1)).findById(1L);
    }
}
