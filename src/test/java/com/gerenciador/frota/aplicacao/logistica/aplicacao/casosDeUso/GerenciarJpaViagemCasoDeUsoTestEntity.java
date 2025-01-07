package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.ViagemRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenciarJpaViagemCasoDeUsoTestEntity {

    @Mock
    private ViagemRepositoryImplementacao viagemRepositoryImplementacao;

    @InjectMocks
    private GerenciarViagemCasoDeUso gerenciarViagemCasoDeUso;

    @Test
    void deveCadastrarNovaViagemComSucesso() {
        // Configuração
        ViagemRequest viagemRequest = ViagemRequest.builder().tipoViagem(TipoViagem.FROTA_AGREGADA).dataViagem(LocalDate.of(2024,10,12)).build();

        JpaViagemEntity jpaViagemEntity = JpaViagemEntity.builder()
                .id(1L)
                .dataCriacao("2024-12-10")
                .dataProgramadaViagem("2024-12-12")
                .volumeTotal(100.0)
                .pesoTotal(500.0)
                .totalKilometragem(1500.0)
                .totalRemessa(10.0)
                .tipoViagem(TipoViagem.FROTA_AGREGADA)
                .build();

        // Mocking do repositório
        when(viagemRepositoryImplementacao.salvar(any(ViagemRequest.class))).thenReturn(Mappers.fromJpaViagemEntityToViagem(jpaViagemEntity));

        // Ação
        Viagem jpaViagemEntityCadastrada = gerenciarViagemCasoDeUso.cadastrarNovaViagem(viagemRequest);

        // Verificação
        assertNotNull(jpaViagemEntityCadastrada);
        assertEquals(TipoViagem.FROTA_AGREGADA, jpaViagemEntityCadastrada.getTipoViagem());
        assertEquals(1500.0, jpaViagemEntityCadastrada.getTotalKilometragem());
        verify(viagemRepositoryImplementacao, times(1)).salvar(any(ViagemRequest.class));
    }

    @Test
    void deveListarTodasAsViagensComSucesso() {
        // Configuração
        List<JpaViagemEntity> listaViagens = List.of(JpaViagemEntity.builder()
                .id(1L)
                .dataCriacao("2024-12-10")
                .dataProgramadaViagem("2024-12-12")
                .volumeTotal(100.0)
                .pesoTotal(500.0)
                .totalKilometragem(1500.0)
                .totalRemessa(10.0)
                .tipoViagem(TipoViagem.FROTA_AGREGADA)
                .build());

        // Mocking do repositório
        when(viagemRepositoryImplementacao.listar()).thenReturn(Mappers.fromListaJpaViagensToListaViagem(listaViagens));

        // Ação
        List<Viagem> viagens = gerenciarViagemCasoDeUso.listarTodasAsViagems();

        // Verificação
        assertNotNull(viagens);
        assertEquals(1, viagens.size());
        verify(viagemRepositoryImplementacao, times(1)).listar();
    }

    @Test
    @Disabled
    void deveListarViagensComFiltroComSucesso() {
        // Configuração
        FiltroViagemRequest filtroViagemRequest = new FiltroViagemRequest();
        Integer page = 0;
        Integer size = 10;
        List<JpaViagemEntity> listaViagens = List.of(JpaViagemEntity.builder()
                .id(1L)
                .dataCriacao("2024-12-10")
                .dataProgramadaViagem("2024-12-12")
                .volumeTotal(100.0)
                .pesoTotal(500.0)
                .totalKilometragem(1500.0)
                .totalRemessa(10.0)
                .tipoViagem(TipoViagem.FROTA_AGREGADA)
                .build());
        PageImpl<JpaViagemEntity> pageViagens = new PageImpl<>(listaViagens);

        // Mocking do repositório e utilitário de paginação
        when(viagemRepositoryImplementacao.listar(filtroViagemRequest)).thenReturn(Mappers.fromListaJpaViagensToListaViagem(listaViagens));

        // Ação
        PageImpl<?> viagensPaginadas = gerenciarViagemCasoDeUso.listarComFiltroViagem(filtroViagemRequest, page, size);

        // Verificação
        assertNotNull(viagensPaginadas);
        assertEquals(1, viagensPaginadas.getContent().size());
        verify(viagemRepositoryImplementacao, times(1)).listar(filtroViagemRequest);
    }

    @Test
    void deveBuscarViagemPorCodigoComSucesso() {
        // Configuração
        Long codigoViagem = 1L;
        JpaViagemEntity jpaViagemEntity = JpaViagemEntity.builder()
                .id(1L)
                .dataCriacao("2024-12-10")
                .dataProgramadaViagem("2024-12-12")
                .volumeTotal(100.0)
                .pesoTotal(500.0)
                .totalKilometragem(1500.0)
                .totalRemessa(10.0)
                .tipoViagem(TipoViagem.FROTA_AGREGADA)
                .build();

        // Mocking do repositório
        when(viagemRepositoryImplementacao.buscarViagemPorId(codigoViagem)).thenReturn(Mappers.fromJpaViagemEntityToViagem(jpaViagemEntity));

        // Ação
        Viagem jpaViagemEntityBuscada = gerenciarViagemCasoDeUso.buscarViagemPorCodigo(codigoViagem);

        // Verificação
        assertNotNull(jpaViagemEntityBuscada);
        assertEquals(TipoViagem.FROTA_AGREGADA, jpaViagemEntity.getTipoViagem());
        assertEquals(1500.0, jpaViagemEntity.getTotalKilometragem());
        verify(viagemRepositoryImplementacao, times(1)).buscarViagemPorId(codigoViagem);
    }

    @Test
    void deveDeletarViagemPorCodigoComSucesso() {
        // Configuração
        Long codigoViagem = 1L;
        RetornoServicoBase retornoServicoBase = RetornoServicoBase.positivo("Viagem deletada com sucesso.");

        // Mocking do repositório
        when(viagemRepositoryImplementacao.deletarViagemPorId(codigoViagem)).thenReturn(retornoServicoBase);

        // Ação
        RetornoServicoBase resultado = gerenciarViagemCasoDeUso.deletarViagemPorCodigo(codigoViagem);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("Viagem deletada com sucesso"));
        verify(viagemRepositoryImplementacao, times(1)).deletarViagemPorId(codigoViagem);
    }
}
