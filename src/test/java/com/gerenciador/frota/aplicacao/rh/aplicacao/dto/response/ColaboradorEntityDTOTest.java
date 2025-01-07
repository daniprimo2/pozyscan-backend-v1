package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColaboradorEntityDTOTest {

    @Test
    void deveConstruirColaboradorDTOCorretamente() {
        // Configuração do ambiente de teste
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setId(1L);

        DadosPessoaisEntity dadosPessoaisEntity = new DadosPessoaisEntity();
        dadosPessoaisEntity.setId(2L);

        ColaboradorEntity colaboradorEntity = new ColaboradorEntity();
        colaboradorEntity.setId(100L);
        colaboradorEntity.setDataContratacao("2024-01-01");
        colaboradorEntity.setDataDemissao("2024-12-31");
        colaboradorEntity.setDescricaoAtividade("Descrição de teste");
        colaboradorEntity.setStatus(StatusColaborador.ATIVO);
        colaboradorEntity.setCargoEntity(cargoEntity);
        colaboradorEntity.setDadosPessoaisEntity(dadosPessoaisEntity);

        // Ação
        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
        colaboradorDTO = colaboradorDTO.construirColaborador(colaboradorEntity);

        // Verificações
        assertNotNull(colaboradorDTO);
        assertEquals(100L, colaboradorDTO.getCodigoColaborador());
        assertEquals("2024-01-01", colaboradorDTO.getDataContratacao());
        assertEquals("2024-12-31", colaboradorDTO.getDataDemissao());
        assertEquals("Descrição de teste", colaboradorDTO.getDescricaoAtividade());
        assertEquals(StatusColaborador.ATIVO, colaboradorDTO.getStatus());
        assertEquals(1L, colaboradorDTO.getCodigoCargo());
        assertEquals(2L, colaboradorDTO.getCodigoDadosPessoais());
    }
}
