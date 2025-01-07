package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.adapters.ViagemRepositoryAdapters;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums.TipoViagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.ViagemRequest;
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
class GerenciarViagemCasoDeUsoTest {

    @Mock
    private ViagemRepositoryAdapters viagemRepositoryAdapters;

    @InjectMocks
    private GerenciarViagemCasoDeUso gerenciarViagemCasoDeUso;

    @Test
    void deveCadastrarNovaViagemComSucesso() {
        // Configuração
        ViagemRequest viagemRequest = ViagemRequest.builder().tipoViagem(TipoViagem.FROTA_AGREGADA).dataViagem(LocalDate.of(2024,10,12)).build();

        Viagem viagem = Viagem.builder()
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
        when(viagemRepositoryAdapters.salvar(any(ViagemRequest.class))).thenReturn(viagem);

        // Ação
        Viagem viagemCadastrada = gerenciarViagemCasoDeUso.cadastrarNovaViagem(viagemRequest);

        // Verificação
        assertNotNull(viagemCadastrada);
        assertEquals(TipoViagem.FROTA_AGREGADA, viagemCadastrada.getTipoViagem());
        assertEquals(1500.0, viagemCadastrada.getTotalKilometragem());
        verify(viagemRepositoryAdapters, times(1)).salvar(any(ViagemRequest.class));
    }

    @Test
    void deveListarTodasAsViagensComSucesso() {
        // Configuração
        List<Viagem> listaViagens = List.of(Viagem.builder()
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
        when(viagemRepositoryAdapters.listar()).thenReturn(listaViagens);

        // Ação
        List<Viagem> viagens = gerenciarViagemCasoDeUso.listarTodasAsViagems();

        // Verificação
        assertNotNull(viagens);
        assertEquals(1, viagens.size());
        verify(viagemRepositoryAdapters, times(1)).listar();
    }

    @Test
    @Disabled
    void deveListarViagensComFiltroComSucesso() {
        // Configuração
        FiltroViagemRequest filtroViagemRequest = new FiltroViagemRequest();
        Integer page = 0;
        Integer size = 10;
        List<Viagem> listaViagens = List.of(Viagem.builder()
                .id(1L)
                .dataCriacao("2024-12-10")
                .dataProgramadaViagem("2024-12-12")
                .volumeTotal(100.0)
                .pesoTotal(500.0)
                .totalKilometragem(1500.0)
                .totalRemessa(10.0)
                .tipoViagem(TipoViagem.FROTA_AGREGADA)
                .build());
        PageImpl<Viagem> pageViagens = new PageImpl<>(listaViagens);

        // Mocking do repositório e utilitário de paginação
        when(viagemRepositoryAdapters.listar(filtroViagemRequest)).thenReturn(listaViagens);

        // Ação
        PageImpl<?> viagensPaginadas = gerenciarViagemCasoDeUso.listarComFiltroViagem(filtroViagemRequest, page, size);

        // Verificação
        assertNotNull(viagensPaginadas);
        assertEquals(1, viagensPaginadas.getContent().size());
        verify(viagemRepositoryAdapters, times(1)).listar(filtroViagemRequest);
    }

    @Test
    void deveBuscarViagemPorCodigoComSucesso() {
        // Configuração
        Long codigoViagem = 1L;
        Viagem viagem = Viagem.builder()
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
        when(viagemRepositoryAdapters.buscarViagemPorId(codigoViagem)).thenReturn(viagem);

        // Ação
        Viagem viagemBuscada = gerenciarViagemCasoDeUso.buscarViagemPorCodigo(codigoViagem);

        // Verificação
        assertNotNull(viagemBuscada);
        assertEquals(TipoViagem.FROTA_AGREGADA, viagem.getTipoViagem());
        assertEquals(1500.0, viagem.getTotalKilometragem());
        verify(viagemRepositoryAdapters, times(1)).buscarViagemPorId(codigoViagem);
    }

    @Test
    void deveDeletarViagemPorCodigoComSucesso() {
        // Configuração
        Long codigoViagem = 1L;
        RetornoServicoBase retornoServicoBase = RetornoServicoBase.positivo("Viagem deletada com sucesso.");

        // Mocking do repositório
        when(viagemRepositoryAdapters.deletarViagemPorId(codigoViagem)).thenReturn(retornoServicoBase);

        // Ação
        RetornoServicoBase resultado = gerenciarViagemCasoDeUso.deletarViagemPorCodigo(codigoViagem);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("Viagem deletada com sucesso"));
        verify(viagemRepositoryAdapters, times(1)).deletarViagemPorId(codigoViagem);
    }
}
