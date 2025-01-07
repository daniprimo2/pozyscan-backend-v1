package com.gerenciador.frota.aplicacao.rh.dominio.entity;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoEntityTest {

    private CargoEntity cargoEntity;

    @BeforeEach
    void setUp() {
        cargoEntity = CargoEntity.builder()
                .id(1L)
                .nomeCargo("Gerente")
                .descricaoCargo("Gerencia a equipe")
                .tipoCargo(TipoCargo.GERENTE)
                .build();
    }

    @Test
    void deveCriarCargoComSucesso() {
        assertNotNull(cargoEntity);
        assertEquals(1L, cargoEntity.getId());
        assertEquals("Gerente", cargoEntity.getNomeCargo());
        assertEquals("Gerencia a equipe", cargoEntity.getDescricaoCargo());
        assertEquals(TipoCargo.GERENTE, cargoEntity.getTipoCargo());
    }

    @Test
    void deveAtualizarNomeDoCargoComSucesso() {
        cargoEntity.setNomeCargo("Supervisor");
        assertEquals("Supervisor", cargoEntity.getNomeCargo());
    }

    @Test
    void deveAtualizarDescricaoDoCargoComSucesso() {
        cargoEntity.setDescricaoCargo("Supervisiona a equipe");
        assertEquals("Supervisiona a equipe", cargoEntity.getDescricaoCargo());
    }

    @Test
    void deveAtualizarTipoDoCargoComSucesso() {
        cargoEntity.setTipoCargo(TipoCargo.SUPERVISOR);
        assertEquals(TipoCargo.SUPERVISOR, cargoEntity.getTipoCargo());
    }

    @Test
    void deveCriarCargoUsandoConstrutor() {
        CargoEntity novoCargoEntity = new CargoEntity("Analista", "Analisa processos", TipoCargo.ANALISTA);
        assertNotNull(novoCargoEntity);
        assertNull(novoCargoEntity.getId());
        assertEquals("Analista", novoCargoEntity.getNomeCargo());
        assertEquals("Analisa processos", novoCargoEntity.getDescricaoCargo());
        assertEquals(TipoCargo.ANALISTA, novoCargoEntity.getTipoCargo());
    }

    @Test
    void deveManterConsistenciaAoUsarGettersESetters() {
        cargoEntity.setId(2L);
        cargoEntity.setNomeCargo("Diretor");
        cargoEntity.setDescricaoCargo("Gerencia a empresa");
        cargoEntity.setTipoCargo(TipoCargo.DIRETOR);

        assertEquals(2L, cargoEntity.getId());
        assertEquals("Diretor", cargoEntity.getNomeCargo());
        assertEquals("Gerencia a empresa", cargoEntity.getDescricaoCargo());
        assertEquals(TipoCargo.DIRETOR, cargoEntity.getTipoCargo());
    }

    @Test
    void deveValidarToString() {
        String esperado = "CargoEntity(id=1, nomeCargo=Gerente, descricaoCargo=Gerencia a equipe, tipoCargo=GERENTE)";
         assertEquals(esperado, cargoEntity.toString());
    }
}
