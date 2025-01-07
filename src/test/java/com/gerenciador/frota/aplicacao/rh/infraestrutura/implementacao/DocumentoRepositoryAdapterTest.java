package com.gerenciador.frota.aplicacao.rh.infraestrutura.implementacao;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.*;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao.ColaboradorRepositoryAdapter;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao.DocumentoRepositoryAdapter;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.ColaboradorRepository;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.DocumentosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class DocumentoRepositoryAdapterTest {

    @Mock
    private DocumentosRepository documentosRepository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    private ColaboradorEntity colaboradorEntity;

    @InjectMocks
    private DocumentoRepositoryAdapter documentoRepositoryAdapter;

    @Mock
    private ColaboradorRepositoryAdapter colaboradorRepositoryAdapter;

    private DocumentosEntity documentosEntity;

    @BeforeEach
    void setUp() {
        documentosEntity = DocumentosEntity.builder()
                .id(1L)
                .tipoDocumento(TipoDocumento.CPF)
                .numeroDocumento("123.456.789-00")
                .build();


        colaboradorEntity =  ColaboradorEntity.builder()
                .id(1l)
                .cargoEntity(montarCargo())
                .dataContratacao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .descricaoAtividade("Trabalhador")
                .emailEmtities(montarListaDeEmails(this.colaboradorEntity))
                .status(StatusColaborador.ATIVO)
                .dadosPessoaisEntity(montarDadosPessoais(montarEndereco()))
                .documentos(montarListaDeDocumentos(this.colaboradorEntity))
                .telefones(montarListaDeTelefones(this.colaboradorEntity))
                .build();
    }

    @Test
    void deveBuscarDocumentoPorTipoComSucesso() {
        // Arrange
        when(documentosRepository.findByTipoDocumento(1L, TipoDocumento.CPF)).thenReturn(documentosEntity);

        // Act
        DocumentosEntity documentoRetornado = documentoRepositoryAdapter.buscarDocumentoPorTipo(1L, TipoDocumento.CPF);

        // Assert
        assertNotNull(documentoRetornado);
        assertEquals(documentosEntity.getNumeroDocumento(), documentoRetornado.getNumeroDocumento());
        verify(documentosRepository, times(1)).findByTipoDocumento(1L, TipoDocumento.CPF);
    }

    @Test
    void deveRetornarNuloAoBuscarDocumentoPorTipoNaoExistente() {
        // Arrange
        when(documentosRepository.findByTipoDocumento(99L, TipoDocumento.CPF)).thenReturn(null);

        // Act
        DocumentosEntity documentoRetornado = documentoRepositoryAdapter.buscarDocumentoPorTipo(99L, TipoDocumento.CPF);

        // Assert
        assertNull(documentoRetornado);
        verify(documentosRepository, times(1)).findByTipoDocumento(99L, TipoDocumento.CPF);
    }

    @Test
    void deveSalvarDocumentoComSucesso() {
        // Arrange
        when(documentosRepository.save(documentosEntity)).thenReturn(documentosEntity);

        // Act
        DocumentosEntity documentoSalvo = documentoRepositoryAdapter.salvar(documentosEntity);

        // Assert
        assertNotNull(documentoSalvo);
        assertEquals(documentosEntity.getNumeroDocumento(), documentoSalvo.getNumeroDocumento());
        verify(documentosRepository, times(1)).save(documentosEntity);
    }

    @Test
    void deveAtualizarDocumentoComSucesso() {
        // Arrange
        DocumentosEntity documentoAtualizado = DocumentosEntity.builder()
                .id(1L)
                .tipoDocumento(TipoDocumento.CPF)
                .numeroDocumento("987.654.321-00")
                .build();

        when(documentosRepository.findByTipoDocumento(1L, TipoDocumento.CPF)).thenReturn(documentosEntity);
        when(documentosRepository.save(any(DocumentosEntity.class))).thenReturn(documentoAtualizado);

        // Act
        DocumentosEntity documentoRetornado = documentoRepositoryAdapter.atualizarDocumento(1L, documentoAtualizado);

        // Assert
        assertNotNull(documentoRetornado);
        assertEquals("987.654.321-00", documentoRetornado.getNumeroDocumento());
        verify(documentosRepository, times(1)).findByTipoDocumento(1L, TipoDocumento.CPF);
        verify(documentosRepository, times(1)).save(any(DocumentosEntity.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarDocumentoNaoExistente() {
        // Arrange
        when(documentosRepository.findByTipoDocumento(1L, TipoDocumento.CPF)).thenReturn(null);

        DocumentosEntity documentoAtualizado = DocumentosEntity.builder()
                .id(1L)
                .tipoDocumento(TipoDocumento.CPF)
                .numeroDocumento("987.654.321-00")
                .build();

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                documentoRepositoryAdapter.atualizarDocumento(1L, documentoAtualizado));
        verify(documentosRepository, times(1)).findByTipoDocumento(1L, TipoDocumento.CPF);
    }

    private List<DocumentosEntity> montarListaDeDocumentos(ColaboradorEntity colaboradorEntityModelo) {
        return Arrays.asList(new DocumentosEntity(5l, "542565487", "15/12/2024", "25/12/2024", "Estado de São Paulo", "", TipoDocumento.RG, colaboradorEntityModelo));
    }

    private List<EmailEmtity> montarListaDeEmails(ColaboradorEntity colaboradorEntityModelo) {
        return Arrays.asList(new EmailEmtity(5L, "daniellsmoreira@gmail.com", "", TipoContato.PESSOAL, colaboradorEntityModelo));
    }

    private List<TelefonesEntity> montarListaDeTelefones(ColaboradorEntity colaboradorEntity) {
        return Arrays.asList(new TelefonesEntity(7l, "11", "985850569", "", TipoContato.PESSOAL, colaboradorEntity));
    }

    private Endereco montarEndereco() {
        return new Endereco(6l, "06385820", "Rua Ipixuna", "154", "Vila Menk", "Carapicuiba", "SP", "São Paulo", "");
    }

    private DadosPessoaisEntity montarDadosPessoais(Endereco endereco) {
        return new DadosPessoaisEntity(2l, "Daniel Lopes Sousa Moreira", "05/08/2024", "Eronice Cristina", "Paulo Roberto", endereco);
    }

    private CargoEntity montarCargo() {
        return new CargoEntity(1l, "Gerente", "Gerentes", TipoCargo.GERENTE);
    }


}

