package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.CargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.FiltroCargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.model.Cargo;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.CargoRepositoryPort;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenciarCargoCasoDeUsoTestEntity {

    @Mock
    private CargoRepositoryPort cargoRepositoryPort;

    @InjectMocks
    private GerenciarCargoCasoDeUso gerenciarCargoCasoDeUso;

    @Test
    void deveCriarCargoComSucesso() {
        // Configuração
        CargoRequest cargoRequest = new CargoRequest();
        cargoRequest.setNomeCargo("Analista de TI");
        cargoRequest.setDescricaoCargo("Responsável por suportar infraestrutura de TI");
        cargoRequest.setTipoCargo(TipoCargo.ANALISTA);

        CargoEntity cargoEntityCriado = new CargoEntity("Analista de TI", "Responsável por suportar infraestrutura de TI", TipoCargo.ASSISTENTE);

        // Mocking do repositório
        when(cargoRepositoryPort.salva(any(CargoEntity.class))).thenReturn(cargoEntityCriado);

        // Ação
        CargoEntity cargoEntity = gerenciarCargoCasoDeUso.criarCargo(cargoRequest);

        // Verificação
        assertNotNull(cargoEntity);
        assertEquals("Analista de TI", cargoEntity.getNomeCargo());
        assertEquals("Responsável por suportar infraestrutura de TI", cargoEntity.getDescricaoCargo());
        verify(cargoRepositoryPort, times(1)).salva(any(CargoEntity.class));
    }

    @Test
    void deveListarTodosCargosComSucesso() {
        // Configuração
        List<CargoEntity> listaCargoEntities = List.of(new CargoEntity("Analista de TI", "Responsável por suportar infraestrutura de TI", TipoCargo.GERENTE));

        // Mocking do repositório
        when(cargoRepositoryPort.listarTodos()).thenReturn(listaCargoEntities);

        // Ação
        List<CargoEntity> cargoEntities = gerenciarCargoCasoDeUso.listarCargos();

        // Verificação
        assertNotNull(cargoEntities);
        assertEquals(1, cargoEntities.size());
        verify(cargoRepositoryPort, times(1)).listarTodos();
    }

    @Test
    @Disabled
    void deveListarCargosComFiltroComSucesso() {
        // Configuração
        FiltroCargoRequest filtroCargoRequest = new FiltroCargoRequest();
        Pageable pageable = mock(Pageable.class);
        List<CargoEntity> listaCargoEntities = List.of(new CargoEntity("Analista de TI", "Responsável por suportar infraestrutura de TI", TipoCargo.SUPERVISOR));
        PageImpl<CargoEntity> page = new PageImpl<>(listaCargoEntities);

        // Mocking do repositório e utilitário de paginação
        when(cargoRepositoryPort.listarPorFiltro(filtroCargoRequest)).thenReturn(listaCargoEntities);
        when(cargoRepositoryPort.listarPorFiltro(filtroCargoRequest)).thenReturn(page.getContent());

        // Ação
        PageImpl<?> cargosPaginados = gerenciarCargoCasoDeUso.listarCargosPorFiltro(filtroCargoRequest, pageable);

        // Verificação
        assertNotNull(cargosPaginados);
        assertEquals(1, cargosPaginados.getContent().size());
        verify(cargoRepositoryPort, times(1)).listarPorFiltro(filtroCargoRequest);
    }

    @Test
    void deveAtualizarCargoComSucesso() {
        // Configuração
        Long codigoCargo = 1L;
        CargoRequest cargoRequest = new CargoRequest();
        cargoRequest.setNomeCargo("Analista de Sistemas");
        cargoRequest.setDescricaoCargo("Responsável pelo desenvolvimento de sistemas");

        RetornoServicoBase retornoServicoBase = RetornoServicoBase.positivo("Cargo atualizado com sucesso.");

        // Mocking do repositório
        when(cargoRepositoryPort.atualizar(codigoCargo, cargoRequest)).thenReturn(retornoServicoBase);

        // Ação
        RetornoServicoBase resultado = gerenciarCargoCasoDeUso.atualizar(codigoCargo, cargoRequest);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("atualizado com sucesso"));
        verify(cargoRepositoryPort, times(1)).atualizar(codigoCargo, cargoRequest);
    }

    @Test
    void deveDesativarCargoComSucesso() {
        // Configuração
        Long codigoCargo = 1L;
        RetornoServicoBase retornoServicoBase = RetornoServicoBase.positivo("Cargo desativado com sucesso.");

        // Mocking do repositório
        when(cargoRepositoryPort.desativar(codigoCargo)).thenReturn(retornoServicoBase);

        // Ação
        RetornoServicoBase resultado = gerenciarCargoCasoDeUso.desativar(codigoCargo);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("desativado com sucesso"));
        verify(cargoRepositoryPort, times(1)).desativar(codigoCargo);
    }

    @Test
    void deveBuscarCargoPorIdComSucesso() {
        // Configuração
        Long codigoCargo = 1L;
        CargoEntity cargoEntity = new CargoEntity("Analista de TI", "Responsável por suportar infraestrutura de TI", TipoCargo.DIRETOR);

        // Mocking do repositório
        when(cargoRepositoryPort.buscarPorId(codigoCargo)).thenReturn(cargoEntity);

        // Ação
        CargoEntity cargoEntityBuscado = gerenciarCargoCasoDeUso.buscarPorId(codigoCargo);

        // Verificação
        assertNotNull(cargoEntityBuscado);
        assertEquals("Analista de TI", cargoEntityBuscado.getNomeCargo());
        verify(cargoRepositoryPort, times(1)).buscarPorId(codigoCargo);
    }
}
