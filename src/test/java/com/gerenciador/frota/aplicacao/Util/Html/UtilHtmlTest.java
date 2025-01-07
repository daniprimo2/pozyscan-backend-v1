package com.gerenciador.frota.aplicacao.Util.Html;

import com.gerenciador.frota.aplicacao.autenticacao.dto.ParametrosEmailRequest;
import com.gerenciador.frota.aplicacao.Util.Strings.Utils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UtilHtmlTest {

    // Testando o método construirParametrosHtml
    @Test
    void testConstruirParametrosHtml() {
        ParametrosEmailRequest parametrosEmail = new ParametrosEmailRequest();
        parametrosEmail.setNomeParticipante("João da Silva");
        parametrosEmail.setLogin("joao.silva");
        parametrosEmail.setSenha("senha123");

        Map<String, String> parametros = UtilHtml.construirParametrosHtml(parametrosEmail);

        assertEquals("João", parametros.get("#nomeParticipante#"));
        assertEquals("joao.silva", parametros.get("#login#"));
        assertEquals("senha123", parametros.get("#senha#"));
    }

    // Testando o método emailHtmlToString
    @Test
    @Disabled("verificar por que nao testa")
    void testEmailHtmlToString_comParametros() {
        // Simulando o conteúdo do arquivo HTML com placeholders
        String templateHtml = "<html><body>Olá, #nomeParticipante#! Seu login é #login# e sua senha é #senha#.</body></html>";

        // Simulando o comportamento do método htmlToString
        ClassPathResource resourceMock = mock(ClassPathResource.class);
        try {
            when(resourceMock.getInputStream()).thenReturn(new java.io.ByteArrayInputStream(templateHtml.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            fail("Erro ao mockar o ClassPathResource");
        }

        // Injetando o mock para a classe
        UtilHtml utilHtmlSpy = spy(UtilHtml.class);
        doReturn(templateHtml).when(utilHtmlSpy).htmlToString("path/to/template.html");

        // Parametros a serem substituídos no template
        Map<String, String> parametros = Map.of(
                "#nomeParticipante#", "João",
                "#login#", "joao.silva",
                "#senha#", "senha123"
        );

        String resultado = utilHtmlSpy.emailHtmlToString("path/to/template.html", parametros);

        // Verificando se os placeholders foram corretamente substituídos
        assertTrue(resultado.contains("Olá, João!"));
        assertTrue(resultado.contains("Seu login é joao.silva"));
        assertTrue(resultado.contains("e sua senha é senha123"));
    }

    @Test
    void testEmailHtmlToString_comTemplateNaoEncontrado() {
        // Simulando a exceção de um template não encontrado
        UtilHtml utilHtml = new UtilHtml();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            utilHtml.emailHtmlToString("path/to/nonexistent_template.html", Map.of());
        });

        assertTrue(exception.getMessage().contains("path/to/nonexistent_template.html"));
    }

    // Testando o método htmlToString
    @Test
    @Disabled("verificar por que nao testa")
    void testHtmlToString_comArquivoValido() throws IOException {
        // Arquivo de exemplo a ser simulado
        String templateHtml = "<html><body>Teste de template</body></html>";

        ClassPathResource resourceMock = mock(ClassPathResource.class);
        when(resourceMock.getInputStream()).thenReturn(new java.io.ByteArrayInputStream(templateHtml.getBytes(StandardCharsets.UTF_8)));

        UtilHtml utilHtml = new UtilHtml();
        String htmlConteudo = utilHtml.htmlToString("path/to/template.html");

        assertEquals(templateHtml, htmlConteudo);
    }

    @Test
    void testHtmlToString_comArquivoInvalido() {
        UtilHtml utilHtml = new UtilHtml();

        // Testando a falha de leitura do arquivo
        assertThrows(RuntimeException.class, () -> {
            utilHtml.htmlToString("invalid/path/to/template.html");
        });
    }
}
