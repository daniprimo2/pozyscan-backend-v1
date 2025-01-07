package com.gerenciador.frota.aplicacao.rh.dominio.entity;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.TelefonesRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.TelefonesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelefonesEntityTest {

    private TelefonesEntity telefone;

    @BeforeEach
    void setUp() {
        telefone = TelefonesEntity.builder()
                .id(1L)
                .dd("11")
                .telefone("987654321")
                .observacoes("Telefone pessoal")
                .tipoContato(TipoContato.PESSOAL)
                .colaboradorEntity(null)
                .build();
    }

    @Test
    void deveCriarTelefoneComSucesso() {
        assertNotNull(telefone);
        assertEquals(1L, telefone.getId());
        assertEquals("11", telefone.getDd());
        assertEquals("987654321", telefone.getTelefone());
        assertEquals("Telefone pessoal", telefone.getObservacoes());
        assertEquals(TipoContato.PESSOAL, telefone.getTipoContato());
        assertNull(telefone.getColaboradorEntity());
    }

    @Test
    void deveAtualizarTelefoneComRequestComSucesso() {
        TelefonesRequest telefonesRequest = TelefonesRequest.builder()
                .dd("21")
                .telefone("123456789")
                .observacoes("Telefone comercial")
                .tipoContato(TipoContato.COMERCIAL)
                .build();

        telefone.atualizarTelefone(telefonesRequest);

        assertEquals("21", telefone.getDd());
        assertEquals("123456789", telefone.getTelefone());
        assertEquals("Telefone comercial", telefone.getObservacoes());
        assertEquals(TipoContato.COMERCIAL, telefone.getTipoContato());
    }

    @Test
    void deveAtualizarTelefoneComCodigoComSucesso() {
        TelefonesEntity novoTelefone = TelefonesEntity.builder()
                .dd("31")
                .telefone("987654321")
                .observacoes("Telefone atualizado")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        telefone.atualizarTelefone(2L, novoTelefone);

        assertEquals(2L, telefone.getId());
        assertEquals("31", telefone.getDd());
        assertEquals("987654321", telefone.getTelefone());
        assertEquals("Telefone atualizado", telefone.getObservacoes());
        assertEquals(TipoContato.PESSOAL, telefone.getTipoContato());
    }

    @Test
    void deveConstruirResponseComSucesso() {
        TelefonesResponse response = telefone.construirResponse();

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("11", response.getDd());
        assertEquals("987654321", response.getTelefone());
        assertEquals("Telefone pessoal", response.getObservacoes());
        assertEquals(TipoContato.PESSOAL, response.getTipoContato());
    }

    @Test
    void deveCriarTelefoneAPartirDeResponse() {
        TelefonesResponse response = TelefonesResponse.builder()
                .id(2L)
                .dd("61")
                .telefone("123123123")
                .observacoes("Telefone de resposta")
                .tipoContato(TipoContato.COMERCIAL)
                .build();

        telefone.fromTelefone(response);

        assertEquals(2L, telefone.getId());
        assertEquals("61", telefone.getDd());
        assertEquals("123123123", telefone.getTelefone());
        assertEquals("Telefone de resposta", telefone.getObservacoes());
        assertEquals(TipoContato.COMERCIAL, telefone.getTipoContato());
    }
}
