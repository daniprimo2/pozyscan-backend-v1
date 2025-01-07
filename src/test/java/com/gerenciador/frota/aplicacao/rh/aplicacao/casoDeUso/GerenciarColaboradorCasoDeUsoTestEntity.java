package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.ColaboradorResponseList;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.ColaboradorRepositoryPort;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenciarColaboradorCasoDeUsoTestEntity {

    @Mock
    private ColaboradorRepositoryPort colaboradorRepositoryPort;

    @Mock
    private GerenciarDocumentoCasoDeUso gerenciarDocumentoCasoDeUso;

    @Mock
    private GerenciarTelefoneCasoDeUso gerenciarTelefoneCasoDeUso;

    @Mock
    private GerenciarEmailCasoDeUso gerenciarEmailCasoDeUso;

    @InjectMocks
    private GerenciarColaboradorCasoDeUso gerenciarColaboradorCasoDeUso;

    @Test
    void deveCriarColaboradorComSucesso() {
        // Configuração
        ColaboradorRequest colaboradorRequest = new ColaboradorRequest();
        ColaboradorEntity colaboradorEntity = new ColaboradorEntity();

        // Mocking do repositório
        when(colaboradorRepositoryPort.salva(colaboradorRequest)).thenReturn(colaboradorEntity);

        // Ação
        ColaboradorEntity colaboradorEntityCriado = gerenciarColaboradorCasoDeUso.criarColaborador(colaboradorRequest);

        // Verificação
        assertNotNull(colaboradorEntityCriado);
        verify(colaboradorRepositoryPort, times(1)).salva(colaboradorRequest);
    }

    @Test
    void deveListarColaboradoresComSucesso() {
        // Configuração
        List<ColaboradorResponseList> listaColaboradores = List.of(new ColaboradorResponseList());

        // Mocking do repositório
        when(colaboradorRepositoryPort.listaDeColaboradores()).thenReturn(listaColaboradores);

        // Ação
        List<ColaboradorResponseList> colaboradores = gerenciarColaboradorCasoDeUso.listaDeColaboradores();

        // Verificação
        assertNotNull(colaboradores);
        assertEquals(1, colaboradores.size());
        verify(colaboradorRepositoryPort, times(1)).listaDeColaboradores();
    }

    @Test
    void deveBuscarColaboradorPorIdComSucesso() {
        // Configuração
        Long id = 1L;
        ColaboradorResponseList colaboradorResponseList = new ColaboradorResponseList();

        // Mocking do repositório
        when(colaboradorRepositoryPort.buscarColaboradorPorId(id)).thenReturn(colaboradorResponseList);

        // Ação
        ColaboradorResponseList colaboradorBuscado = gerenciarColaboradorCasoDeUso.buscarColaboradorPorId(id);

        // Verificação
        assertNotNull(colaboradorBuscado);
        verify(colaboradorRepositoryPort, times(1)).buscarColaboradorPorId(id);
    }

    @Test
    @Disabled
    void deveAtualizarColaboradorComSucesso() {
        // Configuração
        Long id = 1L;
        ColaboradorRequest colaboradorRequest = new ColaboradorRequest();
        ColaboradorResponseList colaboradorResponseList = new ColaboradorResponseList();
        ColaboradorEntity colaboradorEntity = new ColaboradorEntity();

        // Mocking do repositório
        when(colaboradorRepositoryPort.buscarColaboradorPorId(id)).thenReturn(colaboradorResponseList);
        when(colaboradorRepositoryPort.salva(any(ColaboradorEntity.class))).thenReturn(colaboradorEntity);

        // Ação
        RetornoServicoBase resultado = gerenciarColaboradorCasoDeUso.atualizarColaborador(id, colaboradorRequest);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("atualizado com sucesso"));
        verify(colaboradorRepositoryPort, times(1)).salva(any(ColaboradorEntity.class));
    }

    @Test
    void deveDeletarColaboradorComSucesso() {
        // Configuração
        Long id = 1L;

        // Mocking do repositório
        when(colaboradorRepositoryPort.DEletaarColaborador(id)).thenReturn(RetornoServicoBase.positivo("Colaborador deletado com sucesso."));

        // Ação
        RetornoServicoBase resultado = gerenciarColaboradorCasoDeUso.deletarColaborador(id);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("deletado com sucesso"));
        verify(colaboradorRepositoryPort, times(1)).DEletaarColaborador(id);
    }

    @Test
    @Disabled
    void deveLancarErroAoTentarAtualizarColaboradorComIdInexistente() {
        // Configuração
        Long idInexistente = 999L;
        ColaboradorRequest colaboradorRequest = new ColaboradorRequest();

        // Mocking do repositório
        when(colaboradorRepositoryPort.buscarColaboradorPorId(idInexistente)).thenReturn(null);

        // Ação
        RetornoServicoBase resultado = gerenciarColaboradorCasoDeUso.atualizarColaborador(idInexistente, colaboradorRequest);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("Não foi possível atualizar o colaborador"));
        verify(colaboradorRepositoryPort, times(0)).salva(any(ColaboradorEntity.class));  // Não deve chamar salva
    }

    @Test
    void deveLancarErroAoTentarDeletarColaboradorComIdInexistente() {
        // Configuração
        Long idInexistente = 999L;

        // Mocking do repositório
        when(colaboradorRepositoryPort.DEletaarColaborador(idInexistente)).thenReturn(RetornoServicoBase.negativo("Não foi possível deletar o colaborador"));

        // Ação
        RetornoServicoBase resultado = gerenciarColaboradorCasoDeUso.deletarColaborador(idInexistente);

        // Verificação
        assertNotNull(resultado);
        assertTrue(resultado.getDescricao().contains("Não foi possível deletar"));
        verify(colaboradorRepositoryPort, times(1)).DEletaarColaborador(idInexistente);
    }
}
