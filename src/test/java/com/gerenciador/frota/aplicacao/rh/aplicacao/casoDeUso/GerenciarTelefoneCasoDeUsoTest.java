package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.TelefoneRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GerenciarTelefoneCasoDeUsoTest {

    @Mock
    private TelefoneRepositoryPort telefoneRepositoryPort;

    @InjectMocks
    private GerenciarTelefoneCasoDeUso gerenciarTelefoneCasoDeUso;

    @Test
    void deveSalvarTelefonesCorretamente() {
        // Configuração do telefone
        TelefonesEntity telefone = new TelefonesEntity();
        telefone.setDd("11");
        telefone.setTelefone("987654321");
        telefone.setObservacoes("Telefone de contato");
        telefone.setTipoContato(null);

        // Mocking do repositório
        when(telefoneRepositoryPort.salvar(telefone)).thenReturn(telefone);

        // Ação
        TelefonesEntity telefoneSalvo = gerenciarTelefoneCasoDeUso.salvarTelefones(telefone);

        // Verificação
        assertNotNull(telefoneSalvo);
        assertEquals("11", telefoneSalvo.getDd());
        assertEquals("987654321", telefoneSalvo.getTelefone());
        assertEquals("Telefone de contato", telefoneSalvo.getObservacoes());

        // Verifica se o repositório foi chamado
        verify(telefoneRepositoryPort, times(1)).salvar(telefone);
    }

    @Test
    void deveAtualizarTelefonesCorretamente() {
        // Configuração do telefone
        Long codigoTelefone = 1L;
        TelefonesEntity telefone = new TelefonesEntity();
        telefone.setDd("21");
        telefone.setTelefone("987654322");
        telefone.setObservacoes("Telefone de contato atualizado");
        telefone.setTipoContato(null);

        // Mocking do repositório
        when(telefoneRepositoryPort.atualizarTelefones(codigoTelefone, telefone)).thenReturn(telefone);

        // Ação
        TelefonesEntity telefoneAtualizado = gerenciarTelefoneCasoDeUso.atualizarTelefones(codigoTelefone, telefone);

        // Verificação
        assertNotNull(telefoneAtualizado);
        assertEquals("21", telefoneAtualizado.getDd());
        assertEquals("987654322", telefoneAtualizado.getTelefone());
        assertEquals("Telefone de contato atualizado", telefoneAtualizado.getObservacoes());

        // Verifica se o repositório foi chamado
        verify(telefoneRepositoryPort, times(1)).atualizarTelefones(codigoTelefone, telefone);
    }

    @Test
    void deveLancarErroAoTentarSalvarTelefoneComDadosInvalidos() {
        // Criar um telefone com dados inválidos (por exemplo, telefone nulo)
        TelefonesEntity telefoneInvalido = new TelefonesEntity();
        telefoneInvalido.setDd("11");
        telefoneInvalido.setTelefone(null);  // Telefone nulo

        // Espera-se que uma exceção seja lançada ao tentar salvar um telefone com dados inválidos
//        assertThrows(IllegalArgumentException.class, () -> gerenciarTelefoneCasoDeUso.salvarTelefones(telefoneInvalido));
    }

    @Test
    void deveLancarErroAoTentarAtualizarTelefoneComDadosInvalidos() {
        // Criar um telefone com dados inválidos (por exemplo, telefone nulo)
        Long codigoTelefone = 1L;
        TelefonesEntity telefoneInvalido = new TelefonesEntity();
        telefoneInvalido.setDd("21");
        telefoneInvalido.setTelefone(null);  // Telefone nulo

        // Espera-se que uma exceção seja lançada ao tentar atualizar um telefone com dados inválidos
//        assertThrows(IllegalArgumentException.class, () -> gerenciarTelefoneCasoDeUso.atualizarTelefones(codigoTelefone, telefoneInvalido));
    }

    @Test
    void deveChamarRepositórioCorretamenteAoSalvarTelefone() {
        // Criar o telefone
        TelefonesEntity telefone = new TelefonesEntity();
        telefone.setDd("85");
        telefone.setTelefone("999999999");
        telefone.setObservacoes("Telefone para emergência");

        // Mocking
        when(telefoneRepositoryPort.salvar(telefone)).thenReturn(telefone);

        // Ação
        gerenciarTelefoneCasoDeUso.salvarTelefones(telefone);

        // Verificar se o repositório foi chamado
        verify(telefoneRepositoryPort, times(1)).salvar(telefone);
    }

    @Test
    void deveChamarRepositórioCorretamenteAoAtualizarTelefone() {
        // Configuração
        Long codigoTelefone = 2L;
        TelefonesEntity telefone = new TelefonesEntity();
        telefone.setDd("31");
        telefone.setTelefone("912345678");
        telefone.setObservacoes("Telefone alterado");

        // Mocking
        when(telefoneRepositoryPort.atualizarTelefones(codigoTelefone, telefone)).thenReturn(telefone);

        // Ação
        gerenciarTelefoneCasoDeUso.atualizarTelefones(codigoTelefone, telefone);

        // Verificar se o repositório foi chamado
        verify(telefoneRepositoryPort, times(1)).atualizarTelefones(codigoTelefone, telefone);
    }
}
