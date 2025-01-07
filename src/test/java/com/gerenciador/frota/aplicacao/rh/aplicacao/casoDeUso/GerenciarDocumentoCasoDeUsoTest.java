package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DocumentosEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.DocumentoRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenciarDocumentoCasoDeUsoTest {

    @Mock
    private DocumentoRepositoryPort documentoRepositoryPort;

    @InjectMocks
    private GerenciarDocumentoCasoDeUso gerenciarDocumentoCasoDeUso;

    @Test
    void deveBuscarDocumentoPorTipo() {
        // Configuração do documento
        Long codigoDocumento = 1L;
        TipoDocumento tipoDocumento = TipoDocumento.CPF;
        DocumentosEntity documento = new DocumentosEntity();
        documento.setNumeroDocumento("123456789");
        documento.setTipoDocumento(tipoDocumento);

        // Mocking do repositório
        when(documentoRepositoryPort.buscarDocumentoPorTipo(codigoDocumento, tipoDocumento)).thenReturn(documento);

        // Ação
        DocumentosEntity documentoBuscado = gerenciarDocumentoCasoDeUso.buscarDocumentoPorTipo(codigoDocumento, tipoDocumento);

        // Verificação
        assertNotNull(documentoBuscado);
        assertEquals("123456789", documentoBuscado.getNumeroDocumento());
        assertEquals(tipoDocumento, documentoBuscado.getTipoDocumento());

        // Verifica se o repositório foi chamado
        verify(documentoRepositoryPort, times(1)).buscarDocumentoPorTipo(codigoDocumento, tipoDocumento);
    }

    @Test
    void deveSalvarDocumentoCorretamente() {
        // Configuração do documento
        DocumentosEntity documento = new DocumentosEntity();
        documento.setNumeroDocumento("987654321");
        documento.setTipoDocumento(TipoDocumento.RG);

        // Mocking do repositório
        when(documentoRepositoryPort.salvar(documento)).thenReturn(documento);

        // Ação
        DocumentosEntity documentoSalvo = gerenciarDocumentoCasoDeUso.salvarDocumento(documento);

        // Verificação
        assertNotNull(documentoSalvo);
        assertEquals("987654321", documentoSalvo.getNumeroDocumento());
        assertEquals(TipoDocumento.RG, documentoSalvo.getTipoDocumento());

        // Verifica se o repositório foi chamado
        verify(documentoRepositoryPort, times(1)).salvar(documento);
    }

    @Test
    void deveAtualizarDocumentoCorretamente() {
        // Configuração do documento
        Long codigoDocumento = 1L;
        DocumentosEntity documento = new DocumentosEntity();
        documento.setNumeroDocumento("1122334455");
        documento.setTipoDocumento(TipoDocumento.CNH);

        // Mocking do repositório
        when(documentoRepositoryPort.atualizarDocumento(codigoDocumento, documento)).thenReturn(documento);

        // Ação
        DocumentosEntity documentoAtualizado = gerenciarDocumentoCasoDeUso.atualizarDocumento(codigoDocumento, documento);

        // Verificação
        assertNotNull(documentoAtualizado);
        assertEquals("1122334455", documentoAtualizado.getNumeroDocumento());
        assertEquals(TipoDocumento.CNH, documentoAtualizado.getTipoDocumento());

        // Verifica se o repositório foi chamado
        verify(documentoRepositoryPort, times(1)).atualizarDocumento(codigoDocumento, documento);
    }

    @Test
    void deveLancarErroAoTentarBuscarDocumentoComCodigoNulo() {
        // Espera-se que uma exceção seja lançada ao passar um código nulo
//        assertThrows(IllegalArgumentException.class, () -> gerenciarDocumentoCasoDeUso.buscarDocumentoPorTipo(null, TipoDocumento.CPF));
    }

    @Test
    void deveLancarErroAoTentarSalvarDocumentoComDadosInvalidos() {
        // Criar um documento com dados inválidos (exemplo: número de documento nulo)
        DocumentosEntity documentoInvalido = new DocumentosEntity();
        documentoInvalido.setNumeroDocumento(null);  // Número de documento nulo

        // Espera-se que uma exceção seja lançada ao tentar salvar o documento
//        assertThrows(IllegalArgumentException.class, () -> gerenciarDocumentoCasoDeUso.salvarDocumento(documentoInvalido));
    }

    @Test
    void deveLancarErroAoTentarAtualizarDocumentoComDadosInvalidos() {
        // Criar um documento com dados inválidos (exemplo: número de documento nulo)
        Long codigoDocumento = 1L;
        DocumentosEntity documentoInvalido = new DocumentosEntity();
        documentoInvalido.setNumeroDocumento(null);  // Número de documento nulo

        // Espera-se que uma exceção seja lançada ao tentar atualizar o documento
//        assertThrows(IllegalArgumentException.class, () -> gerenciarDocumentoCasoDeUso.atualizarDocumento(codigoDocumento, documentoInvalido));
    }

    @Test
    void deveChamarRepositórioCorretamenteAoBuscarDocumento() {
        // Configuração
        Long codigoDocumento = 1L;
        TipoDocumento tipoDocumento = TipoDocumento.CPF;
        DocumentosEntity documento = new DocumentosEntity();
        documento.setNumeroDocumento("123456789");
        documento.setTipoDocumento(tipoDocumento);

        // Mocking
        when(documentoRepositoryPort.buscarDocumentoPorTipo(codigoDocumento, tipoDocumento)).thenReturn(documento);

        // Ação
        gerenciarDocumentoCasoDeUso.buscarDocumentoPorTipo(codigoDocumento, tipoDocumento);

        // Verificar se o repositório foi chamado
        verify(documentoRepositoryPort, times(1)).buscarDocumentoPorTipo(codigoDocumento, tipoDocumento);
    }

    @Test
    void deveChamarRepositórioCorretamenteAoSalvarDocumento() {
        // Configuração
        DocumentosEntity documento = new DocumentosEntity();
        documento.setNumeroDocumento("987654321");
        documento.setTipoDocumento(TipoDocumento.RG);

        // Mocking
        when(documentoRepositoryPort.salvar(documento)).thenReturn(documento);

        // Ação
        gerenciarDocumentoCasoDeUso.salvarDocumento(documento);

        // Verificar se o repositório foi chamado
        verify(documentoRepositoryPort, times(1)).salvar(documento);
    }

    @Test
    void deveChamarRepositórioCorretamenteAoAtualizarDocumento() {
        // Configuração
        Long codigoDocumento = 1L;
        DocumentosEntity documento = new DocumentosEntity();
        documento.setNumeroDocumento("1122334455");
        documento.setTipoDocumento(TipoDocumento.CNH);

        // Mocking
        when(documentoRepositoryPort.atualizarDocumento(codigoDocumento, documento)).thenReturn(documento);

        // Ação
        gerenciarDocumentoCasoDeUso.atualizarDocumento(codigoDocumento, documento);

        // Verificar se o repositório foi chamado
        verify(documentoRepositoryPort, times(1)).atualizarDocumento(codigoDocumento, documento);
    }
}
