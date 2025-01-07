package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    private Email email;
    private Colaborador colaborador;

    @BeforeEach
    void setUp() {
        colaborador = new Colaborador();
        email = new Email(
                1L,
                "joao.silva@empresa.com",
                "E-mail pessoal",
                TipoContato.PESSOAL,
                colaborador
        );
    }

    @Test
    void deveCriarEmailComSucesso() {
        assertNotNull(email);
        assertEquals(1L, email.getId());
        assertEquals("joao.silva@empresa.com", email.getEmail());
        assertEquals("E-mail pessoal", email.getObservacoes());
        assertEquals(TipoContato.PESSOAL, email.getTipoContato());
        assertEquals(colaborador, email.getColaborador());
    }

    @Test
    void deveAtualizarEmailComSucesso() {
        email.setEmail("novo.email@empresa.com");
        assertEquals("novo.email@empresa.com", email.getEmail());
    }

    @Test
    void deveAtualizarObservacoesComSucesso() {
        email.setObservacoes("E-mail corporativo");
        assertEquals("E-mail corporativo", email.getObservacoes());
    }

    @Test
    void deveAtualizarTipoContatoComSucesso() {
        email.setTipoContato(TipoContato.PESSOAL);
        assertEquals(TipoContato.PESSOAL, email.getTipoContato());
    }

    @Test
    void deveAtualizarColaboradorComSucesso() {
        Colaborador novoColaborador = new Colaborador();
        email.setColaborador(novoColaborador);
        assertEquals(novoColaborador, email.getColaborador());
    }

    @Test
    void deveCriarEmailUsandoConstrutorVazio() {
        Email novoEmail = new Email();
        assertNull(novoEmail.getId());
        assertNull(novoEmail.getEmail());
        assertNull(novoEmail.getObservacoes());
        assertNull(novoEmail.getTipoContato());
        assertNull(novoEmail.getColaborador());
    }
}
