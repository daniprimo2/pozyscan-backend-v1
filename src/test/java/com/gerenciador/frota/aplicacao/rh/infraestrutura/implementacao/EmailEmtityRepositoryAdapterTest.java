package com.gerenciador.frota.aplicacao.rh.infraestrutura.implementacao;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao.EmailRepositoryAdapter;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.EmailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailEmtityRepositoryAdapterTest {

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailRepositoryAdapter emailRepositoryAdapter;

    private EmailEmtity emailEmtity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        emailEmtity = EmailEmtity.builder()
                .id(1L)
                .email("teste@exemplo.com")
                .tipoContato(TipoContato.PESSOAL)
                .build();
    }

    @Test
    void deveBuscarEmailPorTipoComSucesso() {
        when(emailRepository.findByCodigoEmailAndTipoContato(1L, "PESSOAL")).thenReturn(emailEmtity);

        EmailEmtity resultado = emailRepositoryAdapter.buscarEmailPorTipo(1L, TipoContato.PESSOAL);

        assertNotNull(resultado);
        assertEquals("teste@exemplo.com", resultado.getEmail());
        assertEquals(TipoContato.PESSOAL, resultado.getTipoContato());
        verify(emailRepository, times(1)).findByCodigoEmailAndTipoContato(1L, "PESSOAL");
    }

    @Test
    void deveRetornarNuloQuandoEmailNaoForEncontrado() {
        when(emailRepository.findByCodigoEmailAndTipoContato(1L, "PESSOAL")).thenReturn(null);

        EmailEmtity resultado = emailRepositoryAdapter.buscarEmailPorTipo(1L, TipoContato.PESSOAL);

        assertNull(resultado);
        verify(emailRepository, times(1)).findByCodigoEmailAndTipoContato(1L, "PESSOAL");
    }

    @Test
    void deveSalvarEmailComSucesso() {
        when(emailRepository.save(emailEmtity)).thenReturn(emailEmtity);

        EmailEmtity resultado = emailRepositoryAdapter.salvar(emailEmtity);

        assertNotNull(resultado);
        assertEquals("teste@exemplo.com", resultado.getEmail());
        verify(emailRepository, times(1)).save(emailEmtity);
    }

    @Test
    void deveAtualizarEmailComSucesso() {
        when(emailRepository.findByCodigoEmailAndTipoContato(1L, "PESSOAL")).thenReturn(emailEmtity);
        when(emailRepository.save(any(EmailEmtity.class))).thenReturn(emailEmtity);

        EmailEmtity emailEmtityAtualizado = EmailEmtity.builder()
                .id(1L)
                .email("novoemail@exemplo.com")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        EmailEmtity resultado = emailRepositoryAdapter.atualizarEmail(1L, emailEmtityAtualizado);

        assertNotNull(resultado);
        assertEquals("novoemail@exemplo.com", resultado.getEmail());
        verify(emailRepository, times(1)).findByCodigoEmailAndTipoContato(1L, "PESSOAL");
        verify(emailRepository, times(1)).save(any(EmailEmtity.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarEmailNaoEncontrado() {
        when(emailRepository.findByCodigoEmailAndTipoContato(1L, "PESSOAL")).thenReturn(null);

        EmailEmtity emailEmtityAtualizado = EmailEmtity.builder()
                .id(1L)
                .email("novoemail@exemplo.com")
                .tipoContato(TipoContato.PESSOAL)
                .build();

        assertThrows(RuntimeException.class, () -> emailRepositoryAdapter.atualizarEmail(1L, emailEmtityAtualizado));
        verify(emailRepository, times(1)).findByCodigoEmailAndTipoContato(1L, "PESSOAL");
    }
}
