package com.gerenciador.frota.aplicacao.rh.dominio.entity;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.EmailRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.EmailResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailEmtityTest {

    private EmailEmtity emailEmtity;

    @BeforeEach
    void setUp() {
        emailEmtity = EmailEmtity.builder()
                .id(1L)
                .email("example@example.com")
                .observacoes("E-mail corporativo")
                .tipoContato(TipoContato.PESSOAL)
                .colaboradorEntity(null)
                .build();
    }

    @Test
    void deveCriarEmailComSucesso() {
        assertNotNull(emailEmtity);
        assertEquals(1L, emailEmtity.getId());
        assertEquals("example@example.com", emailEmtity.getEmail());
        assertEquals("E-mail corporativo", emailEmtity.getObservacoes());
        assertEquals(TipoContato.PESSOAL, emailEmtity.getTipoContato());
        assertNull(emailEmtity.getColaboradorEntity());
    }

    @Test
    void deveAtualizarEmailComRequestComSucesso() {
        EmailRequest emailRequest = EmailRequest.builder()
                .email("new@example.com")
                .observacoes("E-mail alternativo")
                .tipoContato(TipoContato.COMERCIAL)
                .build();

        emailEmtity.atualizarEmail(emailRequest);

        assertEquals("new@example.com", emailEmtity.getEmail());
        assertEquals("E-mail alternativo", emailEmtity.getObservacoes());
        assertEquals(TipoContato.COMERCIAL, emailEmtity.getTipoContato());
    }

    @Test
    void deveAtualizarEmailComCodigoComSucesso() {
        EmailEmtity novoEmailEmtity = EmailEmtity.builder()
                .email("updated@example.com")
                .observacoes("E-mail atualizado")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        emailEmtity.atualizarEmail(2L, novoEmailEmtity);

        assertEquals(2L, emailEmtity.getId());
        assertEquals("updated@example.com", emailEmtity.getEmail());
        assertEquals("E-mail atualizado", emailEmtity.getObservacoes());
        assertEquals(TipoContato.PESSOAL, emailEmtity.getTipoContato());
    }

    @Test
    void deveConstruirResponseComSucesso() {
        EmailResponse response = emailEmtity.construirResponse();

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("example@example.com", response.getEmail());
        assertEquals("E-mail corporativo", response.getObservacoes());
        assertEquals(TipoContato.PESSOAL, response.getTipoContato());
    }

    @Test
    void deveCriarEmailAPartirDeResponse() {
        EmailResponse response = EmailResponse.builder()
                .id(2L)
                .email("response@example.com")
                .observacoes("E-mail gerado a partir de resposta")
                .tipoContato(TipoContato.COMERCIAL)
                .build();

        emailEmtity.fromEmail(response);

        assertEquals(2L, emailEmtity.getId());
        assertEquals("response@example.com", emailEmtity.getEmail());
        assertEquals("E-mail gerado a partir de resposta", emailEmtity.getObservacoes());
        assertEquals(TipoContato.COMERCIAL, emailEmtity.getTipoContato());
    }
}
