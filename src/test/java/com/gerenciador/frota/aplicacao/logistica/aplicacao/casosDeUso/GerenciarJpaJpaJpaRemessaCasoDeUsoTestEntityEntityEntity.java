package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.Remessa;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.RemessaRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenciarJpaJpaRemessaCasoDeUsoTestEntityEntity {

    private GerenciarRemessaCasoDeUso casoDeUso;
    private RemessaRepositoryImplementacao remessaRepositoryImplementacao;

    @BeforeEach
    void setUp() {
        remessaRepositoryImplementacao = Mockito.mock(RemessaRepositoryImplementacao.class);
        casoDeUso = new GerenciarRemessaCasoDeUso(remessaRepositoryImplementacao);
    }

    @Test
    void deveCadastrarNovaRemessaComSucesso() {
        RemessaRequest request = RemessaRequest.builder()
                .cliente("Cliente A")
                .build();
        Remessa remessa = Remessa.builder()
                .cliente("Cliente A")
                .build();

        when(remessaRepositoryImplementacao.salvar(request)).thenReturn(remessa);

        Remessa resultado = casoDeUso.cadastrarNovaRemessa(request);

        assertNotNull(resultado);
        assertEquals("Cliente A", resultado.getCliente());
        verify(remessaRepositoryImplementacao, times(1)).salvar(request);
    }

    @Test
    void deveListarTodasRemessasComSucesso() {
        List<Remessa> jpaRemessaEntities = new ArrayList<>();
        jpaRemessaEntities.add(Remessa.builder().cliente("Cliente A").build());

        when(remessaRepositoryImplementacao.listarTodasRemessas()).thenReturn(jpaRemessaEntities);

        List<Remessa> resultado = casoDeUso.listarTodasRemessas();

        assertEquals(1, resultado.size());
        assertEquals("Cliente A", resultado.get(0).getCliente());
        verify(remessaRepositoryImplementacao, times(1)).listarTodasRemessas();
    }

    @Test
    void deveListarRemessasPorStatusComSucesso() {
        List<Remessa> jpaRemessaEntities = new ArrayList<>();
        jpaRemessaEntities.add(Remessa.builder().statusRemessa(StatusRemessa.VAZIA).build());

        when(remessaRepositoryImplementacao.listarTodasRemessasPorStatus(StatusRemessa.VAZIA)).thenReturn(jpaRemessaEntities);

        List<Remessa> resultado = casoDeUso.listarTodasRemessasPorTipo(StatusRemessa.VAZIA);

        assertEquals(1, resultado.size());
        assertEquals(StatusRemessa.VAZIA, resultado.get(0).getStatusRemessa());
        verify(remessaRepositoryImplementacao, times(1)).listarTodasRemessasPorStatus(StatusRemessa.VAZIA);
    }

    @Test
    void deveBuscarRemessaPorIdComSucesso() {
//        JpaRemessaEntity jpaRemessaEntity = JpaRemessaEntity.builder().id(1L).cliente("Cliente A").build();
        com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa remessa = new com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa();
        remessa.setId(1l);
        remessa.setCliente("Cliente A");
        when(remessaRepositoryImplementacao.buscarRemessaPorId(1L)).thenReturn(remessa);

        Remessa resultado = casoDeUso.buscarRemessaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cliente A", resultado.getCliente());
        verify(remessaRepositoryImplementacao, times(1)).buscarRemessaPorId(1L);
    }

    @Test
    void deveAtualizarRemessaComSucesso() {
//        JpaRemessaEntity jpaRemessaEntity = JpaRemessaEntity.builder().id(1L).cliente("Cliente A").build();
        RemessaRequest request = RemessaRequest.builder().cliente("Cliente B").build();
        com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa remessa = new com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa();
        remessa.setId(1l);
        remessa.setCliente("Cliente A");

        when(remessaRepositoryImplementacao.buscarRemessaPorId(1L)).thenReturn(remessa);
        when(remessaRepositoryImplementacao.salvar(any(Remessa.class))).thenReturn(RetornoServicoBase.positivo("Atualizado com sucesso."));

        RetornoServicoBase resultado = casoDeUso.atualziarRemessaPorId(1L, request);

        assertTrue(resultado.getFuncionou());
        assertEquals("Atualizado com sucesso.", resultado.getDescricao());
        verify(remessaRepositoryImplementacao, times(1)).buscarRemessaPorId(1L);
        verify(remessaRepositoryImplementacao, times(1)).salvar(any(Remessa.class));
    }

    @Test
    void deveDeletarRemessaComSucesso() {
        when(remessaRepositoryImplementacao.deletarRemessaPorId(1L)).thenReturn(RetornoServicoBase.positivo("Deletado com sucesso."));

        RetornoServicoBase resultado = casoDeUso.deletarRemessa(1L);

        assertTrue(resultado.getFuncionou());
        assertEquals("Deletado com sucesso.", resultado.getDescricao());
        verify(remessaRepositoryImplementacao, times(1)).deletarRemessaPorId(1L);
    }
}
