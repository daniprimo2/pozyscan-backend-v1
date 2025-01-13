package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.ProdutoRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenciarProdutoCasoDeUsoTest {

    @Mock
    private ProdutoRepositoryImplementacao produtoRepositoryImplementacao;

    @InjectMocks
    private GerenciarProdutoCasoDeUso gerenciarProdutoCasoDeUso;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarNovaProduto() {
        ProdutoRequest request = ProdutoRequest.builder()
                .nomeProduto("Produto Teste")
                .descricaoProduto("Descrição Teste")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(10)
                .build();

        Produto mockProduto = Produto.builder()
                .nomeProduto("Produto Teste")
                .descricaoProduto("Descrição Teste")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(10)
                .build();

        when(produtoRepositoryImplementacao.cadastrarProduto(request)).thenReturn(mockProduto);

        Produto result = gerenciarProdutoCasoDeUso.cadastrarNovaProduto(request);

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNomeProduto());
        verify(produtoRepositoryImplementacao, times(1)).cadastrarProduto(request);
    }

    @Test
    void listaDeProdutos() {
        Produto mockProduto = Produto.builder()
                .nomeProduto("Produto Teste")
                .descricaoProduto("Descrição Teste")
                .build();

        when(produtoRepositoryImplementacao.listarProdutos()).thenReturn(Collections.singletonList(mockProduto));

        List<Produto> result = gerenciarProdutoCasoDeUso.listaDeProdutos();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(produtoRepositoryImplementacao, times(1)).listarProdutos();
    }

    @Test
    void buscarProdutoPorId() {
        Long id = 1L;
        Produto mockProduto = Produto.builder()
                .codigoProduto(id)
                .nomeProduto("Produto Teste")
                .build();

        when(produtoRepositoryImplementacao.buscarProdutoPorCodigo(id)).thenReturn(mockProduto);

        Produto result = gerenciarProdutoCasoDeUso.buscarProdutoPorId(id);

        assertNotNull(result);
        assertEquals(id, result.getCodigoProduto());
        verify(produtoRepositoryImplementacao, times(1)).buscarProdutoPorCodigo(id);
    }

    @Test
    void atualizarProdutoPorId() {
        Long id = 1L;
        ProdutoRequest request = ProdutoRequest.builder()
                .nomeProduto("Produto Atualizado")
                .descricaoProduto("Descrição Atualizada")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(5)
                .build();

        RetornoServicoBase mockRetorno = RetornoServicoBase.positivo("Produto atualizado com sucesso.");

        when(produtoRepositoryImplementacao.atuallizarProdutoPorCodigo(id, request)).thenReturn(mockRetorno);

        RetornoServicoBase result = gerenciarProdutoCasoDeUso.atualizarProdutoPorId(id, request);

        assertNotNull(result);
        assertTrue(result.getFuncionou());
        assertEquals("Produto atualizado com sucesso.", result.getDescricao());
        verify(produtoRepositoryImplementacao, times(1)).atuallizarProdutoPorCodigo(id, request);
    }

    @Test
    void deletarProduto() {
        Long id = 1L;
        RetornoServicoBase mockRetorno = RetornoServicoBase.positivo("Produto deletado com sucesso.");

        when(produtoRepositoryImplementacao.deletarProdutoPorCodigo(id)).thenReturn(mockRetorno);

        RetornoServicoBase result = gerenciarProdutoCasoDeUso.deletarProduto(id);

        assertNotNull(result);
        assertTrue(result.getFuncionou());
        assertEquals("Produto deletado com sucesso.", result.getDescricao());
        verify(produtoRepositoryImplementacao, times(1)).deletarProdutoPorCodigo(id);
    }
}
