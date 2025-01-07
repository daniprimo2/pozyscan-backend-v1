package com.gerenciador.frota.aplicacao.rh.infraestrutura.implementacao;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao.TelefonesRepositoryAdapter;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.TelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TelefonesEntityRepositoryAdapterTest {

    @Mock
    private TelRepository telRepository;

    @InjectMocks
    private TelefonesRepositoryAdapter telefonesRepositoryAdapter;

    private TelefonesEntity telefone;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        telefone = TelefonesEntity.builder()
                .id(1L)
                .dd("11")
                .telefone("999999999")
                .tipoContato(TipoContato.PESSOAL)
                .build();
    }

    @Test
    void deveBuscarTelefonePorTipoComSucesso() {
        when(telRepository.findByCodigoTelefoneAndTipoContato(1L, "PESSOAL")).thenReturn(telefone);

        TelefonesEntity resultado = telefonesRepositoryAdapter.buscarTelefonesPorTipo(1L, TipoContato.PESSOAL);

        assertNotNull(resultado);
        assertEquals("999999999", resultado.getTelefone());
        assertEquals("11", resultado.getDd());
        assertEquals(TipoContato.PESSOAL, resultado.getTipoContato());
        verify(telRepository, times(1)).findByCodigoTelefoneAndTipoContato(1L, "PESSOAL");
    }

    @Test
    void deveRetornarNuloQuandoTelefoneNaoForEncontrado() {
        when(telRepository.findByCodigoTelefoneAndTipoContato(1L, "PESSOAL")).thenReturn(null);

        TelefonesEntity resultado = telefonesRepositoryAdapter.buscarTelefonesPorTipo(1L, TipoContato.PESSOAL);

        assertNull(resultado);
        verify(telRepository, times(1)).findByCodigoTelefoneAndTipoContato(1L, "PESSOAL");
    }

    @Test
    void deveSalvarTelefoneComSucesso() {
        when(telRepository.save(telefone)).thenReturn(telefone);

        TelefonesEntity resultado = telefonesRepositoryAdapter.salvar(telefone);

        assertNotNull(resultado);
        assertEquals("999999999", resultado.getTelefone());
        verify(telRepository, times(1)).save(telefone);
    }

    @Test
    void deveAtualizarTelefoneComSucesso() {
        when(telRepository.findByCodigoTelefoneAndTipoContato(1L, "PESSOAL")).thenReturn(telefone);
        when(telRepository.save(any(TelefonesEntity.class))).thenReturn(telefone);

        TelefonesEntity telefoneAtualizado = TelefonesEntity.builder()
                .id(1L)
                .dd("21")
                .telefone("888888888")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        TelefonesEntity resultado = telefonesRepositoryAdapter.atualizarTelefones(1L, telefoneAtualizado);

        assertNotNull(resultado);
        assertEquals("21", resultado.getDd());
        assertEquals("888888888", resultado.getTelefone());
        verify(telRepository, times(1)).findByCodigoTelefoneAndTipoContato(1L, "PESSOAL");
        verify(telRepository, times(1)).save(any(TelefonesEntity.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarTelefoneNaoEncontrado() {
        when(telRepository.findByCodigoTelefoneAndTipoContato(1L, "PESSOAL")).thenReturn(null);

        TelefonesEntity telefoneAtualizado = TelefonesEntity.builder()
                .id(1L)
                .dd("21")
                .telefone("888888888")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        assertThrows(RuntimeException.class, () -> telefonesRepositoryAdapter.atualizarTelefones(1L, telefoneAtualizado));
        verify(telRepository, times(1)).findByCodigoTelefoneAndTipoContato(1L, "PESSOAL");
    }
}
