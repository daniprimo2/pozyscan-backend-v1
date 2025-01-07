package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.ColaboradorEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelefonesEntityRequestTest {

    @Test
    void deveCadastrarTelefoneCorretamente() {
        // Configuração do ambiente de teste
        ColaboradorEntity colaboradorEntity = new ColaboradorEntity();
        colaboradorEntity.setId(1L);

        TelefonesRequest telefonesRequest = TelefonesRequest.builder()
                .dd("11")
                .telefone("987654321")
                .observacoes("Telefone residencial")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        // Ação
        TelefonesEntity telefone = telefonesRequest.cadastrarTelefone(colaboradorEntity);

        // Verificações
        assertNotNull(telefone);
        assertEquals("11", telefone.getDd());
        assertEquals("987654321", telefone.getTelefone());
        assertEquals("Telefone residencial", telefone.getObservacoes());
        assertEquals(TipoContato.PESSOAL, telefone.getTipoContato());
        assertEquals(colaboradorEntity, telefone.getColaboradorEntity());
    }

    @Test
    void deveConstruirTelefoneComDdCorretamente() {
        // Configuração do ambiente de teste
        ColaboradorEntity colaboradorEntity = new ColaboradorEntity();
        colaboradorEntity.setId(2L);

        TelefonesRequest telefonesRequest = TelefonesRequest.builder()
                .dd("21")
                .build();

        // Ação
        TelefonesEntity telefone = telefonesRequest.construirTelefone(colaboradorEntity);

        // Verificações
        assertNotNull(telefone);
        assertEquals("21", telefone.getDd());
        assertNull(telefone.getTelefone());
        assertNull(telefone.getObservacoes());
        assertNull(telefone.getTipoContato());
        assertEquals(colaboradorEntity, telefone.getColaboradorEntity());
    }
}
