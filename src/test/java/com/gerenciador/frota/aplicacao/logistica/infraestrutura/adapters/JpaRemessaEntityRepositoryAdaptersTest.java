package com.gerenciador.frota.aplicacao.logistica.infraestrutura.adapters;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.RemessaRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaRemessaRepository;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaRemessaEntityRepositoryAdaptersTest {

    private JpaRemessaRepository jpaRemessaRepository;
    private RemessaRepositoryImplementacao remessaRepositoryImplementacao;
    private Remessa remessa;
    private JpaRemessaEntity jpaRemessaEntity;


    @BeforeEach
    void setUp() {

        jpaRemessaRepository = Mockito.mock(JpaRemessaRepository.class);
        remessaRepositoryImplementacao = new RemessaRepositoryImplementacao(jpaRemessaRepository);

        MockitoAnnotations.openMocks(this);

        // Criando mock do objeto Remessa
        remessa = new Remessa();
        remessa.setCliente("Cliente Teste");
        remessa.setId(1L);

        // Criando mock da entidade JpaRemessaEntity
        jpaRemessaEntity = new JpaRemessaEntity();
        jpaRemessaEntity.setId(1L);
        jpaRemessaEntity.setCliente("Cliente Teste");
    }

    @Test
    void deveSalvarRemessaComSucesso() {
        JpaRemessaEntity jpaRemessaEntity = JpaRemessaEntity.builder()
                .cliente("Cliente A")
                .statusRemessa(StatusRemessa.VAZIA)
                .build();

        when(jpaRemessaRepository.save(any(JpaRemessaEntity.class))).thenReturn(jpaRemessaEntity);

        RemessaRequest request = RemessaRequest.builder()
                .cliente("Cliente A")
                .build();

        RemessaResponse resultado = remessaRepositoryImplementacao.salvar(request);

        assertNotNull(resultado);
        assertEquals("Cliente A", resultado.getCliente());
        verify(jpaRemessaRepository, times(1)).save(any(JpaRemessaEntity.class));
    }

    @Test
    void deveLancarExcecaoAoSalvarRemessa() {
        when(jpaRemessaRepository.save(any(JpaRemessaEntity.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        RemessaRequest request = RemessaRequest.builder().cliente("Cliente A").build();

        Exception exception = assertThrows(RuntimeException.class, () -> remessaRepositoryImplementacao.salvar(request));

        assertEquals("Não foi possivel cadastrar a remessa.", exception.getMessage());
    }

    @Test
    void deveAtualizarRemessaComSucesso() {
        JpaRemessaEntity jpaRemessaEntity = JpaRemessaEntity.builder().id(1L).cliente("Cliente A").build();
        when(jpaRemessaRepository.save(any(JpaRemessaEntity.class))).thenReturn(jpaRemessaEntity);

        RetornoServicoBase resultado = remessaRepositoryImplementacao.salvar(jpaRemessaEntity);

        assertTrue(resultado.getFuncionou());
        assertEquals("Remessa atualizada com sucesso.", resultado.getDescricao());
        verify(jpaRemessaRepository, times(1)).save(any(JpaRemessaEntity.class));
    }

    @Test
    void deveRetornarErroAoAtualizarRemessa() {
        when(jpaRemessaRepository.save(any(JpaRemessaEntity.class))).thenThrow(new RuntimeException("Erro ao atualizar"));

        JpaRemessaEntity jpaRemessaEntity = JpaRemessaEntity.builder().id(1L).cliente("Cliente A").build();

        RetornoServicoBase resultado = remessaRepositoryImplementacao.salvar(jpaRemessaEntity);

        assertFalse(resultado.getFuncionou());
        assertEquals("Remessa não pode ser atualizada.", resultado.getDescricao());
    }

    @Test
    void deveListarTodasRemessasComSucesso() {
        List<JpaRemessaEntity> jpaJpaRemessaEntityEntities = new ArrayList<>();
        jpaJpaRemessaEntityEntities.add(JpaRemessaEntity.builder().cliente("Cliente A").build());

        when(jpaRemessaRepository.findAll()).thenReturn(jpaJpaRemessaEntityEntities);

        List<Remessa> resultado = remessaRepositoryImplementacao.listarTodasRemessas();

        assertEquals(1, resultado.size());
        assertEquals("Cliente A", resultado.get(0).getCliente());
    }

    @Test
    void deveBuscarRemessaPorIdComSucesso() {
        JpaRemessaEntity jpaRemessaEntity = JpaRemessaEntity.builder().id(1L).cliente("Cliente A").build();

        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.of(jpaRemessaEntity));

        com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa resultado = remessaRepositoryImplementacao.buscarRemessaPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Cliente A", resultado.getCliente());
    }

    @Test
    void deveLancarExcecaoAoBuscarRemessaPorIdNaoEncontrado() {
        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> remessaRepositoryImplementacao.buscarRemessaPorId(1L));

        assertEquals("Remessa com codigo fornecido não encontrado.", exception.getMessage());
    }

    @Test
    @Disabled("Nao esta sendo possivel criar este teste")
    void deveDeletarRemessaPorIdComSucesso() {
        // Mockando o retorno do findById
        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.of(jpaRemessaEntity));

        // Mockando o comportamento do delete
        doNothing().when(jpaRemessaRepository).delete(any(JpaRemessaEntity.class));

        // Executando o método
        RetornoServicoBase resultado = remessaRepositoryImplementacao.deletarRemessaPorId(1L);

        // Verificando o resultado esperado
        assertEquals("Remessa: 1 foi deletada com sucesso.", resultado.getDescricao());

        // Verificando se o delete foi chamado corretamente
        verify(jpaRemessaRepository, times(1)).delete(jpaRemessaEntity);
    }

    @Test
    void deveRetornarErroAoDeletarRemessaNaoEncontrada() {
        when(jpaRemessaRepository.findById(1L)).thenReturn(Optional.empty());

        RetornoServicoBase resultado = remessaRepositoryImplementacao.deletarRemessaPorId(1L);

        assertFalse(resultado.getFuncionou());
        assertEquals("Remessa: 1 não foi deletada.", resultado.getDescricao());
    }

    @Test
    void deveListarRemessasPorStatusComSucesso() {
        List<JpaRemessaEntity> jpaJpaRemessaEntityEntities = new ArrayList<>();
        jpaJpaRemessaEntityEntities.add(JpaRemessaEntity.builder().statusRemessa(StatusRemessa.VAZIA).build());

        when(jpaRemessaRepository.findAllStatus("VAZIA")).thenReturn(jpaJpaRemessaEntityEntities);

        List<Remessa> resultado = remessaRepositoryImplementacao.listarTodasRemessasPorStatus(StatusRemessa.VAZIA);

        assertEquals(1, resultado.size());
        assertEquals(StatusRemessa.VAZIA, resultado.get(0).getStatusRemessa());
    }
}
