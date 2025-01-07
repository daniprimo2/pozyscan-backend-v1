package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.RemessaRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenciarJpaJpaJpaRemessaCasoDeUsoTestEntityEntityEntity {

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
        JpaRemessaEntity jpaRemessaEntity = JpaRemessaEntity.builder()
                .cliente("Cliente A")
                .build();

        when(remessaRepositoryImplementacao.salvar(request)).thenReturn(Mappers.fromJpaRemessaEntityToRemessaResponse(jpaRemessaEntity));

        RemessaResponse resultado = casoDeUso.cadastrarNovaRemessa(request);

        assertNotNull(resultado);
        assertEquals("Cliente A", resultado.getCliente());
        verify(remessaRepositoryImplementacao, times(1)).salvar(request);
    }

    @Test
    void deveListarTodasRemessasComSucesso() {
//        List<JpaRemessaEntity> jpaJpaRemessaEntityEntities = new ArrayList<>();
//        jpaJpaRemessaEntityEntities.add(JpaRemessaEntity.builder().cliente("Cliente A").build());
        Remessa remessa = new Remessa();
        remessa.setId(1L);
        remessa.setCliente("Cliente A");
        List<Remessa> jpaJpaRemessaEntityEntities = new ArrayList<>();
        jpaJpaRemessaEntityEntities.add(remessa);

        when(remessaRepositoryImplementacao.listarTodasRemessas()).thenReturn(jpaJpaRemessaEntityEntities);

        List<Remessa> resultado = casoDeUso.listarTodasRemessas();

        assertEquals(1, resultado.size());
        assertEquals("Cliente A", resultado.get(0).getCliente());
        verify(remessaRepositoryImplementacao, times(1)).listarTodasRemessas();
    }

    @Test
    void deveListarRemessasPorStatusComSucesso() {
        Remessa remessa = new Remessa();
        remessa.setId(1L);
        remessa.setCliente("Cliente A");
        remessa.setStatusRemessa(StatusRemessa.VAZIA);

        List<Remessa> jpaJpaRemessaEntityEntities = new ArrayList<>();
        jpaJpaRemessaEntityEntities.add(remessa);

        when(remessaRepositoryImplementacao.listarTodasRemessasPorStatus(StatusRemessa.VAZIA)).thenReturn(jpaJpaRemessaEntityEntities);

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
        when(remessaRepositoryImplementacao.salvar(any(JpaRemessaEntity.class))).thenReturn(RetornoServicoBase.positivo("Atualizado com sucesso."));

        RetornoServicoBase resultado = casoDeUso.atualziarRemessaPorId(1L, request);

        assertTrue(resultado.getFuncionou());
        assertEquals("Atualizado com sucesso.", resultado.getDescricao());
        verify(remessaRepositoryImplementacao, times(1)).buscarRemessaPorId(1L);
        verify(remessaRepositoryImplementacao, times(1)).salvar(any(JpaRemessaEntity.class));
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
