package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoTest {

    private Cargo cargo;

    @BeforeEach
    void setUp() {
        cargo = new Cargo(1L, "Gerente", "Responsável pela equipe", TipoCargo.ADMINISTRATIVO);
    }

    @Test
    void deveCriarCargoComSucesso() {
        assertNotNull(cargo);
        assertEquals(1L, cargo.getId());
        assertEquals("Gerente", cargo.getNomeCargo());
        assertEquals("Responsável pela equipe", cargo.getDescricaoCargo());
        assertEquals(TipoCargo.ADMINISTRATIVO, cargo.getTipoCargo());
    }

    @Test
    void deveAtualizarNomeCargoComSucesso() {
        cargo.setNomeCargo("Supervisor");
        assertEquals("Supervisor", cargo.getNomeCargo());
    }

    @Test
    void deveAtualizarDescricaoCargoComSucesso() {
        cargo.setDescricaoCargo("Supervisiona a equipe");
        assertEquals("Supervisiona a equipe", cargo.getDescricaoCargo());
    }

    @Test
    void deveAtualizarTipoCargoComSucesso() {
        cargo.setTipoCargo(TipoCargo.GERENTE);
        assertEquals(TipoCargo.GERENTE, cargo.getTipoCargo());
    }

    @Test
    void deveManterConsistenciaAoUsarGettersESetters() {
        cargo.setId(2L);
        cargo.setNomeCargo("Analista");
        cargo.setDescricaoCargo("Analisa processos");
        cargo.setTipoCargo(TipoCargo.GERENTE);

        assertEquals(2L, cargo.getId());
        assertEquals("Analista", cargo.getNomeCargo());
        assertEquals("Analisa processos", cargo.getDescricaoCargo());
        assertEquals(TipoCargo.GERENTE, cargo.getTipoCargo());
    }

    @Test
    void devePermitirCriacaoComConstrutorVazio() {
        Cargo novoCargo = new Cargo();
        assertNull(novoCargo.getId());
        assertNull(novoCargo.getNomeCargo());
        assertNull(novoCargo.getDescricaoCargo());
        assertNull(novoCargo.getTipoCargo());

        novoCargo.setId(3L);
        novoCargo.setNomeCargo("Auxiliar");
        novoCargo.setDescricaoCargo("Auxilia nas atividades");
        novoCargo.setTipoCargo(TipoCargo.GERENTE);

        assertEquals(3L, novoCargo.getId());
        assertEquals("Auxiliar", novoCargo.getNomeCargo());
        assertEquals("Auxilia nas atividades", novoCargo.getDescricaoCargo());
        assertEquals(TipoCargo.GERENTE, novoCargo.getTipoCargo());
    }
}
