package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelefonesTest {

    private Telefones telefone;
    private Colaborador colaborador;

    @BeforeEach
    void setUp() {
        colaborador = new Colaborador();
        telefone = new Telefones(
                1L,
                "11",
                "99999-9999",
                "Telefone celular pessoal",
                TipoContato.PESSOAL,
                colaborador
        );
    }

    @Test
    void deveCriarTelefoneComSucesso() {
        assertNotNull(telefone);
        assertEquals(1L, telefone.getId());
        assertEquals("11", telefone.getDd());
        assertEquals("99999-9999", telefone.getTelefone());
        assertEquals("Telefone celular pessoal", telefone.getObservacoes());
        assertEquals(TipoContato.PESSOAL, telefone.getTipoContato());
        assertEquals(colaborador, telefone.getColaborador());
    }

    @Test
    void deveAtualizarDdComSucesso() {
        telefone.setDd("21");
        assertEquals("21", telefone.getDd());
    }

    @Test
    void deveAtualizarTelefoneComSucesso() {
        telefone.setTelefone("98888-8888");
        assertEquals("98888-8888", telefone.getTelefone());
    }

    @Test
    void deveAtualizarObservacoesComSucesso() {
        telefone.setObservacoes("Telefone de trabalho");
        assertEquals("Telefone de trabalho", telefone.getObservacoes());
    }

    @Test
    void deveAtualizarTipoContatoComSucesso() {
        telefone.setTipoContato(TipoContato.PESSOAL);
        assertEquals(TipoContato.PESSOAL, telefone.getTipoContato());
    }

    @Test
    void deveAtualizarColaboradorComSucesso() {
        Colaborador novoColaborador = new Colaborador();
        telefone.setColaborador(novoColaborador);
        assertEquals(novoColaborador, telefone.getColaborador());
    }

    @Test
    void deveCriarTelefoneUsandoConstrutorVazio() {
        Telefones novoTelefone = new Telefones();
        assertNull(novoTelefone.getId());
        assertNull(novoTelefone.getDd());
        assertNull(novoTelefone.getTelefone());
        assertNull(novoTelefone.getObservacoes());
        assertNull(novoTelefone.getTipoContato());
        assertNull(novoTelefone.getColaborador());
    }
}
