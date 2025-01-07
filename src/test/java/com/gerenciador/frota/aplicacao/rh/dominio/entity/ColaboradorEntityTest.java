package com.gerenciador.frota.aplicacao.rh.dominio.entity;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarDocumentoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarEmailCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarTelefoneCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ColaboradorEntityTest {

    private ColaboradorEntity colaboradorEntity;

    @Mock
    private GerenciarDocumentoCasoDeUso gerenciarDocumentoCasoDeUso;

    @Mock
    private GerenciarTelefoneCasoDeUso gerenciarTelefoneCasoDeUso;

    @Mock
    private GerenciarEmailCasoDeUso gerenciarEmailCasoDeUso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        colaboradorEntity = ColaboradorEntity.builder()
                .id(1L)
                .descricaoAtividade("Atividade inicial")
                .status(StatusColaborador.ATIVO)
                .dataContratacao("2023-01-01")
                .dadosPessoaisEntity(DadosPessoaisEntity.builder()
                        .id(1L)
                        .nomeCompleto("Nome Completo")
                        .build())
                .build();
    }

    @Test
    void deveAtualizarDadosPessoaisComSucesso() {
        DadosPessoaisRequest request = DadosPessoaisRequest.builder()
                .nomeCompleto("Novo Nome")
                .nomeDaMae("Nova Mãe")
                .nomeDoPai("Novo Pai")
                .dataNascimento("2000-01-01")
                .build();

        colaboradorEntity.atualizarDadosPessoais(request);

        assertEquals("Novo Nome", colaboradorEntity.getDadosPessoaisEntity().getNomeCompleto());
        assertEquals("Nova Mãe", colaboradorEntity.getDadosPessoaisEntity().getNomeMae());
        assertEquals("Novo Pai", colaboradorEntity.getDadosPessoaisEntity().getNomePai());
    }

    @Test
    void deveAtualizarDocumentosComSucesso() {
        DocumentosRequest request = DocumentosRequest.builder()
                .tipoDocumento(TipoDocumento.RG)
                .numeroDocumento("123456")
                .build();

        when(gerenciarDocumentoCasoDeUso.atualizarDocumento(anyLong(), any())).thenReturn(null);

        colaboradorEntity.setDocumentos(List.of(DocumentosEntity.builder()
                .id(1L)
                .tipoDocumento(TipoDocumento.RG)
                .build()));

        colaboradorEntity.atualizarDocumentos(List.of(request), gerenciarDocumentoCasoDeUso);

        verify(gerenciarDocumentoCasoDeUso, times(1))
                .atualizarDocumento(eq(1L), any(DocumentosEntity.class));
    }

    @Test
    void deveAtualizarTelefonesComSucesso() {
        TelefonesRequest request = TelefonesRequest.builder()
                .telefone("123456789")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        colaboradorEntity.setTelefones(List.of(TelefonesEntity.builder()
                .id(1L)
                .tipoContato(TipoContato.PESSOAL)
                .telefone("987654321")
                .build()));

        colaboradorEntity.atualizarTelefones(List.of(request), gerenciarTelefoneCasoDeUso);

        verify(gerenciarTelefoneCasoDeUso, times(1))
                .atualizarTelefones(eq(1L), any(TelefonesEntity.class));
    }

    @Test
    void deveLancarExcecaoQuandoTipoContatoNaoInformadoEmTelefone() {
        TelefonesRequest request = TelefonesRequest.builder()
                .telefone("123456789")
                .tipoContato(null)
                .build();

        Exception exception = assertThrows(RuntimeException.class, () ->
                colaboradorEntity.atualizarTelefones(List.of(request), gerenciarTelefoneCasoDeUso));

        assertEquals("Deve se informadp o tipo de contato.", exception.getMessage());
    }

    @Test
    void deveAtualizarEmailsComSucesso() {
        EmailRequest request = EmailRequest.builder()
                .email("novoemail@test.com")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        colaboradorEntity.setEmailEmtities(List.of(EmailEmtity.builder()
                .id(1L)
                .tipoContato(TipoContato.PESSOAL)
                .email("antigoemail@test.com")
                .build()));

        colaboradorEntity.atualizarEmails(List.of(request), gerenciarEmailCasoDeUso);

        verify(gerenciarEmailCasoDeUso, times(1))
                .atualizarEmail(eq(1L), any(EmailEmtity.class));
    }
}
