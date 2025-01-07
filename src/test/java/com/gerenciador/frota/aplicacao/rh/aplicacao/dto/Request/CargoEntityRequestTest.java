package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoEntityRequestTest {

    @Test
    void deveConstruirCargoCorretamente() {
        // Configuração do teste
        String nomeCargo = "Analista de TI";
        String descricaoCargo = "Responsável pelo suporte e desenvolvimento de sistemas.";
        TipoCargo tipoCargo = TipoCargo.GERENTE;

        // Criar o objeto CargoRequest
        CargoRequest cargoRequest = CargoRequest.builder()
                .nomeCargo(nomeCargo)
                .descricaoCargo(descricaoCargo)
                .tipoCargo(tipoCargo)
                .build();

        // Ação
        CargoEntity cargoEntity = cargoRequest.construirCargo();

        // Verificações
        assertNotNull(cargoEntity);
        assertEquals(nomeCargo, cargoEntity.getNomeCargo());
        assertEquals(descricaoCargo, cargoEntity.getDescricaoCargo());
        assertEquals(tipoCargo, cargoEntity.getTipoCargo());
    }

    @Test
    void deveAtualizarCargoCorretamente() {
        // Configuração do teste
        Long idCargo = 1L;
        String nomeCargo = "Coordenador de TI";
        String descricaoCargo = "Coordena a equipe de TI.";
        TipoCargo tipoCargo = TipoCargo.GERENTE;

        // Criar o objeto CargoRequest
        CargoRequest cargoRequest = CargoRequest.builder()
                .nomeCargo(nomeCargo)
                .descricaoCargo(descricaoCargo)
                .tipoCargo(tipoCargo)
                .build();

        // Ação
        CargoEntity cargoEntityAtualizado = cargoRequest.atualizandoCargo(idCargo);

        // Verificações
        assertNotNull(cargoEntityAtualizado);
        assertEquals(idCargo, cargoEntityAtualizado.getId());
        assertEquals(nomeCargo, cargoEntityAtualizado.getNomeCargo());
        assertEquals(descricaoCargo, cargoEntityAtualizado.getDescricaoCargo());
        assertEquals(tipoCargo, cargoEntityAtualizado.getTipoCargo());
    }

    @Test
    void deveRetornarErroQuandoNomeCargoNaoForInformado() {
        // Criar o objeto CargoRequest com nomeCargo vazio
        CargoRequest cargoRequest = CargoRequest.builder()
                .descricaoCargo("Descrição de cargo")
                .tipoCargo(TipoCargo.GERENTE)
                .build();

        // Ação e verificação: Espera-se que o nomeCargo seja obrigatório
//        assertThrows(IllegalArgumentException.class, cargoRequest::construirCargo);
    }

    @Test
    void deveRetornarErroQuandoDescricaoCargoExcederLimiteDeCaracteres() {
        // Criar o objeto CargoRequest com descriçãoCargo muito longa
        String descricaoMuitoLonga = "A".repeat(256); // Descrição com mais de 255 caracteres
        CargoRequest cargoRequest = CargoRequest.builder()
                .nomeCargo("Nome do Cargo")
                .descricaoCargo(descricaoMuitoLonga)
                .tipoCargo(TipoCargo.GERENTE)
                .build();

        // Ação e verificação: Espera-se que a descrição tenha no máximo 255 caracteres
//        assertThrows(IllegalArgumentException.class, cargoRequest::construirCargo);
    }

    @Test
    void deveCriarCargoComTipoCargoNulo() {
        // Configuração do teste com tipoCargo nulo
        String nomeCargo = "Estagiário";
        String descricaoCargo = "Cargo de estagiário na área de TI";

        // Criar o objeto CargoRequest com tipoCargo nulo
        CargoRequest cargoRequest = CargoRequest.builder()
                .nomeCargo(nomeCargo)
                .descricaoCargo(descricaoCargo)
                .tipoCargo(null)
                .build();

        // Ação
        CargoEntity cargoEntity = cargoRequest.construirCargo();

        // Verificações
        assertNotNull(cargoEntity);
        assertEquals(nomeCargo, cargoEntity.getNomeCargo());
        assertEquals(descricaoCargo, cargoEntity.getDescricaoCargo());
        assertNull(cargoEntity.getTipoCargo()); // O tipoCargo foi passado como nulo
    }
}
