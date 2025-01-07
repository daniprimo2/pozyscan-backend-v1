package com.gerenciador.frota.aplicacao.logistica.dominio.model;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JpaRemessaEntityTest {

    private JpaRemessaEntity jpaRemessaEntity;

    @BeforeEach
    void setUp() {
        jpaRemessaEntity = JpaRemessaEntity.builder()
                .id(1L)
                .cliente("Cliente A")
                .dataCriacao("2024-12-01")
                .volumeTotal(120.5)
                .pesoTotal(300.0)
                .statusRemessa(StatusRemessa.VAZIA)
                .jpaNotaFiscalLogisticaEntities(new ArrayList<>())
                .build();
    }

    @Test
    void deveCriarRemessaComSucesso() {
        assertNotNull(jpaRemessaEntity);
        assertEquals(1L, jpaRemessaEntity.getId());
        assertEquals("Cliente A", jpaRemessaEntity.getCliente());
        assertEquals("2024-12-01", jpaRemessaEntity.getDataCriacao());
        assertEquals(120.5, jpaRemessaEntity.getVolumeTotal());
        assertEquals(300.0, jpaRemessaEntity.getPesoTotal());
        assertEquals(StatusRemessa.VAZIA, jpaRemessaEntity.getStatusRemessa());
        assertTrue(jpaRemessaEntity.getJpaNotaFiscalLogisticaEntities().isEmpty());
    }

    @Test
    void deveAtualizarClienteComSucesso() {
        jpaRemessaEntity.setCliente("Cliente B");
        assertEquals("Cliente B", jpaRemessaEntity.getCliente());
    }

    @Test
    void deveAtualizarVolumeETotalComSucesso() {
        jpaRemessaEntity.setVolumeTotal(200.0);
        jpaRemessaEntity.setPesoTotal(500.0);

        assertEquals(200.0, jpaRemessaEntity.getVolumeTotal());
        assertEquals(500.0, jpaRemessaEntity.getPesoTotal());
    }

    @Test
    void deveAtualizarStatusRemessaComSucesso() {
        jpaRemessaEntity.setStatusRemessa(StatusRemessa.CONCLUIDA);
        assertEquals(StatusRemessa.CONCLUIDA, jpaRemessaEntity.getStatusRemessa());
    }

    @Test
    void deveAssociarNotasFiscaisComSucesso() {
        JpaNotaFiscalLogisticaEntity nota1 = new JpaNotaFiscalLogisticaEntity();
        JpaNotaFiscalLogisticaEntity nota2 = new JpaNotaFiscalLogisticaEntity();

        jpaRemessaEntity.getJpaNotaFiscalLogisticaEntities().add(nota1);
        jpaRemessaEntity.getJpaNotaFiscalLogisticaEntities().add(nota2);

        assertEquals(2, jpaRemessaEntity.getJpaNotaFiscalLogisticaEntities().size());
        assertTrue(jpaRemessaEntity.getJpaNotaFiscalLogisticaEntities().contains(nota1));
        assertTrue(jpaRemessaEntity.getJpaNotaFiscalLogisticaEntities().contains(nota2));
    }

    @Test
    void deveAtualizarRemessaComDadosDoRequest() {
        RemessaRequest request = RemessaRequest.builder()
                .cliente("Cliente Atualizado")
                .build();

        jpaRemessaEntity.atualizarRemessa(request);

        assertEquals("Cliente Atualizado", jpaRemessaEntity.getCliente());
    }

    @Test
    void deveCriarRemessaUsandoConstrutor() {
        JpaRemessaEntity novaJpaRemessaEntity = new JpaRemessaEntity(
                null,
                "Cliente Novo",
                "2024-12-02",
                150.0,
                400.0,
                StatusRemessa.VAZIA,
                null,
                new ArrayList<>()
        );

        assertNotNull(novaJpaRemessaEntity);
        assertNull(novaJpaRemessaEntity.getId());
        assertEquals("Cliente Novo", novaJpaRemessaEntity.getCliente());
        assertEquals("2024-12-02", novaJpaRemessaEntity.getDataCriacao());
        assertEquals(150.0, novaJpaRemessaEntity.getVolumeTotal());
        assertEquals(400.0, novaJpaRemessaEntity.getPesoTotal());
        assertEquals(StatusRemessa.VAZIA, novaJpaRemessaEntity.getStatusRemessa());
    }

    @Test
    void deveManterConsistenciaAoUsarGettersESetters() {
        jpaRemessaEntity.setId(2L);
        jpaRemessaEntity.setCliente("Novo Cliente");
        jpaRemessaEntity.setDataCriacao("2024-12-03");
        jpaRemessaEntity.setVolumeTotal(250.0);
        jpaRemessaEntity.setPesoTotal(600.0);
        jpaRemessaEntity.setStatusRemessa(StatusRemessa.CANCELADA);

        assertEquals(2L, jpaRemessaEntity.getId());
        assertEquals("Novo Cliente", jpaRemessaEntity.getCliente());
        assertEquals("2024-12-03", jpaRemessaEntity.getDataCriacao());
        assertEquals(250.0, jpaRemessaEntity.getVolumeTotal());
        assertEquals(600.0, jpaRemessaEntity.getPesoTotal());
        assertEquals(StatusRemessa.CANCELADA, jpaRemessaEntity.getStatusRemessa());
    }

    @Test
    void deveValidarToString() {
        String esperado = "Remessa(id=1, cliente=Cliente A, dataCriacao=2024-12-01, volumeTotal=120.5, pesoTotal=300.0, statusRemessa=VAZIA, viagem=null, notaFiscalLogisticas=[])";
        assertEquals(esperado, jpaRemessaEntity.toString());
    }
}
