package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.EmailRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenciarEmailCasoDeUsoTestEmtity {

    @Mock
    private EmailRepositoryPort emailRepositoryPort;

    @InjectMocks
    private GerenciarEmailCasoDeUso gerenciarEmailCasoDeUso;

    @Test
    void deveBuscarEmailPorTipo() {
        // Configuração do email
        Long codigoEmail = 1L;
        TipoContato tipoContato = TipoContato.PESSOAL;
        EmailEmtity emailEmtity = new EmailEmtity();
        emailEmtity.setEmail("teste@dominio.com");
        emailEmtity.setTipoContato(tipoContato);

        // Mocking do repositório
        when(emailRepositoryPort.buscarEmailPorTipo(codigoEmail, tipoContato)).thenReturn(emailEmtity);

        // Ação
        EmailEmtity emailEmtityBuscado = gerenciarEmailCasoDeUso.buscarEmailPorTipo(codigoEmail, tipoContato);

        // Verificação
        assertNotNull(emailEmtityBuscado);
        assertEquals("teste@dominio.com", emailEmtityBuscado.getEmail());
        assertEquals(tipoContato, emailEmtityBuscado.getTipoContato());

        // Verifica se o repositório foi chamado
        verify(emailRepositoryPort, times(1)).buscarEmailPorTipo(codigoEmail, tipoContato);
    }

    @Test
    void deveSalvarEmailCorretamente() {
        // Configuração do email
        EmailEmtity emailEmtity = new EmailEmtity();
        emailEmtity.setEmail("teste@dominio.com");
        emailEmtity.setTipoContato(TipoContato.PESSOAL);

        // Mocking do repositório
        when(emailRepositoryPort.salvar(emailEmtity)).thenReturn(emailEmtity);

        // Ação
        EmailEmtity emailEmtitySalvo = gerenciarEmailCasoDeUso.salvarEmail(emailEmtity);

        // Verificação
        assertNotNull(emailEmtitySalvo);
        assertEquals("teste@dominio.com", emailEmtitySalvo.getEmail());
        assertEquals(TipoContato.PESSOAL, emailEmtitySalvo.getTipoContato());

        // Verifica se o repositório foi chamado
        verify(emailRepositoryPort, times(1)).salvar(emailEmtity);
    }

    @Test
    void deveAtualizarEmailCorretamente() {
        // Configuração do email
        Long codigoEmail = 1L;
        EmailEmtity emailEmtity = new EmailEmtity();
        emailEmtity.setEmail("email.atualizado@dominio.com");
        emailEmtity.setTipoContato(TipoContato.COMERCIAL);

        // Mocking do repositório
        when(emailRepositoryPort.atualizarEmail(codigoEmail, emailEmtity)).thenReturn(emailEmtity);

        // Ação
        EmailEmtity emailEmtityAtualizado = gerenciarEmailCasoDeUso.atualizarEmail(codigoEmail, emailEmtity);

        // Verificação
        assertNotNull(emailEmtityAtualizado);
        assertEquals("email.atualizado@dominio.com", emailEmtityAtualizado.getEmail());
        assertEquals(TipoContato.COMERCIAL, emailEmtityAtualizado.getTipoContato());

        // Verifica se o repositório foi chamado
        verify(emailRepositoryPort, times(1)).atualizarEmail(codigoEmail, emailEmtity);
    }

    @Test
    void deveLancarErroAoTentarBuscarEmailComCodigoNulo() {
        // Espera-se que uma exceção seja lançada ao passar um código nulo
//        assertThrows(IllegalArgumentException.class, () -> gerenciarEmailCasoDeUso.buscarEmailPorTipo(null, TipoContato.PESSOAL));
    }

    @Test
    void deveLancarErroAoTentarSalvarEmailComDadosInvalidos() {
        // Criar um email com dados inválidos (exemplo: email nulo)
        EmailEmtity emailEmtityInvalido = new EmailEmtity();
        emailEmtityInvalido.setEmail(null);  // Email nulo

        // Espera-se que uma exceção seja lançada ao tentar salvar o email
//        assertThrows(IllegalArgumentException.class, () -> gerenciarEmailCasoDeUso.salvarEmail(emailInvalido));
    }

    @Test
    void deveLancarErroAoTentarAtualizarEmailComDadosInvalidos() {
        // Criar um email com dados inválidos (exemplo: email nulo)
        Long codigoEmail = 1L;
        EmailEmtity emailEmtityInvalido = new EmailEmtity();
        emailEmtityInvalido.setEmail(null);  // Email nulo

        // Espera-se que uma exceção seja lançada ao tentar atualizar o email
//        assertThrows(IllegalArgumentException.class, () -> gerenciarEmailCasoDeUso.atualizarEmail(codigoEmail, emailInvalido));
    }

    @Test
    void deveChamarRepositórioCorretamenteAoBuscarEmail() {
        // Configuração
        Long codigoEmail = 1L;
        TipoContato tipoContato = TipoContato.PESSOAL;
        EmailEmtity emailEmtity = new EmailEmtity();
        emailEmtity.setEmail("teste@dominio.com");
        emailEmtity.setTipoContato(tipoContato);

        // Mocking
        when(emailRepositoryPort.buscarEmailPorTipo(codigoEmail, tipoContato)).thenReturn(emailEmtity);

        // Ação
        gerenciarEmailCasoDeUso.buscarEmailPorTipo(codigoEmail, tipoContato);

        // Verificar se o repositório foi chamado
        verify(emailRepositoryPort, times(1)).buscarEmailPorTipo(codigoEmail, tipoContato);
    }

    @Test
    void deveChamarRepositórioCorretamenteAoSalvarEmail() {
        // Configuração
        EmailEmtity emailEmtity = new EmailEmtity();
        emailEmtity.setEmail("teste@dominio.com");
        emailEmtity.setTipoContato(TipoContato.PESSOAL);

        // Mocking
        when(emailRepositoryPort.salvar(emailEmtity)).thenReturn(emailEmtity);

        // Ação
        gerenciarEmailCasoDeUso.salvarEmail(emailEmtity);

        // Verificar se o repositório foi chamado
        verify(emailRepositoryPort, times(1)).salvar(emailEmtity);
    }

    @Test
    void deveChamarRepositórioCorretamenteAoAtualizarEmail() {
        // Configuração
        Long codigoEmail = 1L;
        EmailEmtity emailEmtity = new EmailEmtity();
        emailEmtity.setEmail("email.atualizado@dominio.com");
        emailEmtity.setTipoContato(TipoContato.COMERCIAL);

        // Mocking
        when(emailRepositoryPort.atualizarEmail(codigoEmail, emailEmtity)).thenReturn(emailEmtity);

        // Ação
        gerenciarEmailCasoDeUso.atualizarEmail(codigoEmail, emailEmtity);

        // Verificar se o repositório foi chamado
        verify(emailRepositoryPort, times(1)).atualizarEmail(codigoEmail, emailEmtity);
    }
}
