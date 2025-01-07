package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailEmtityRequestTest {

    @Test
    void deveConstruirEmailCorretamente() {
        // Configuração do ambiente de teste
        String emailEndereco = "exemplo@teste.com";
        String observacoes = "Observação de teste";
        TipoContato tipoContato = TipoContato.PESSOAL;

        ColaboradorEntity colaboradorEntity = ColaboradorEntity.builder()
                .id(1L)
                .descricaoAtividade("Atividade inicial")
                .status(StatusColaborador.ATIVO)
                .dataContratacao("2023-01-01")
                .dadosPessoaisEntity(DadosPessoaisEntity.builder()
                        .id(1L)
                        .nomeCompleto("Nome Completo")
                        .build())
                .build();

        // Criar o objeto EmailRequest
        EmailRequest emailRequest = EmailRequest.builder()
                .email(emailEndereco)
                .observacoes(observacoes)
                .tipoContato(tipoContato)
                .build();

        // Ação
        EmailEmtity emailEmtity = emailRequest.construirEmails(colaboradorEntity);

        // Verificações
        assertNotNull(emailEmtity);
        assertEquals(emailEndereco, emailEmtity.getEmail());
        assertEquals(observacoes, emailEmtity.getObservacoes());
        assertEquals(tipoContato, emailEmtity.getTipoContato());
        assertEquals(colaboradorEntity, emailEmtity.getColaboradorEntity());
    }

    @Test
    void deveRetornarEmailComCamposNullSeNaoForPreenchido() {
        // Criar o objeto EmailRequest sem alguns valores preenchidos
        EmailRequest emailRequest = EmailRequest.builder()
                .email("exemplo@teste.com")
                .build();

        // Configuração do colaborador
        ColaboradorEntity colaboradorEntity = ColaboradorEntity.builder()
                .id(1L)
                .descricaoAtividade("Atividade inicial")
                .status(StatusColaborador.ATIVO)
                .dataContratacao("2023-01-01")
                .dadosPessoaisEntity(DadosPessoaisEntity.builder()
                        .id(1L)
                        .nomeCompleto("Nome Completo")
                        .build())
                .build();

        // Ação
        EmailEmtity emailEmtity = emailRequest.construirEmails(colaboradorEntity);

        // Verificações
        assertNotNull(emailEmtity);
        assertEquals("exemplo@teste.com", emailEmtity.getEmail());
        assertNull(emailEmtity.getObservacoes());  // Observações não foram definidas, deve ser null
        assertNull(emailEmtity.getTipoContato());  // TipoContato não foi definido, deve ser null
        assertEquals(colaboradorEntity, emailEmtity.getColaboradorEntity());
    }

}
