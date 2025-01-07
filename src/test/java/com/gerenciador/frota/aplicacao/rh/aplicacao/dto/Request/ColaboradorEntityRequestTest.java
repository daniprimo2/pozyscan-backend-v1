package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarCargoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ColaboradorEntityRequestTest {

    @Test
    void deveConstruirColaboradorCorretamente() {
        // Configuração do ambiente de teste
        Long codigoDoCargo = 1L;
        String descricaoAtividade = "Atividade do Colaborador";

        // Mock do caso de uso GerenciarCargoCasoDeUso
        GerenciarCargoCasoDeUso cargoInfraestrutura = Mockito.mock(GerenciarCargoCasoDeUso.class);

        // Mock de um objeto Cargo retornado pelo GerenciarCargoCasoDeUso
        CargoEntity cargoEntity =  CargoEntity.builder()
                .id(1L)
                .nomeCargo("Gerente")
                .descricaoCargo("Gerencia a equipe")
                .tipoCargo(TipoCargo.GERENTE)
                .build();
        // Definindo o comportamento do mock
        Mockito.when(cargoInfraestrutura.buscarPorId(codigoDoCargo)).thenReturn(cargoEntity);

        // Criar o objeto ColaboradorRequest
        ColaboradorRequest colaboradorRequest = ColaboradorRequest.builder()
                .codigoDoCargo(codigoDoCargo)
                .descricaoAtividade(descricaoAtividade)
                .build();

        // Ação
        ColaboradorEntity colaboradorEntity = colaboradorRequest.construirColaborador(cargoInfraestrutura);

        // Verificações
        assertNotNull(colaboradorEntity);
        assertEquals(StatusColaborador.ATIVO, colaboradorEntity.getStatus());
        assertEquals(descricaoAtividade, colaboradorEntity.getDescricaoAtividade());
        assertEquals(cargoEntity, colaboradorEntity.getCargoEntity());
        assertEquals(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()), colaboradorEntity.getDataContratacao());
    }

    @Test
    void deveRetornarColaboradorComDataContratacaoAtual() {
        // Criar o objeto ColaboradorRequest com todos os dados necessários
        Long codigoDoCargo = 2L;
        String descricaoAtividade = "Atividade do Colaborador";

        // Mock do caso de uso GerenciarCargoCasoDeUso
        GerenciarCargoCasoDeUso cargoInfraestrutura = Mockito.mock(GerenciarCargoCasoDeUso.class);

        // Mock de um objeto Cargo
        CargoEntity cargoEntity =  CargoEntity.builder()
                .id(1L)
                .nomeCargo("Gerente")
                .descricaoCargo("Gerencia a equipe")
                .tipoCargo(TipoCargo.GERENTE)
                .build();

        // Definindo o comportamento do mock
        Mockito.when(cargoInfraestrutura.buscarPorId(codigoDoCargo)).thenReturn(cargoEntity);

        // Criar o objeto ColaboradorRequest
        ColaboradorRequest colaboradorRequest = ColaboradorRequest.builder()
                .codigoDoCargo(codigoDoCargo)
                .descricaoAtividade(descricaoAtividade)
                .build();

        // Ação
        ColaboradorEntity colaboradorEntity = colaboradorRequest.construirColaborador(cargoInfraestrutura);

        // Verificação da data de contratação
        assertNotNull(colaboradorEntity.getDataContratacao());
        assertEquals(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()), colaboradorEntity.getDataContratacao());
    }
}
