package com.gerenciador.frota.aplicacao.logistica.adapters.inbound;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso.GerenciarProdutoCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @Mock
    private GerenciarProdutoCasoDeUso gerenciarProdutoCasoDeUso;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cadastrarNovaRemessa() {
        // Arrange
        ProdutoRequest request = ProdutoRequest.builder()
                .nomeProduto("Produto Teste")
                .descricaoProduto("Descrição Teste")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(10)
                .build();

        Produto mockProduto = Produto.builder()
                .codigoProduto(1L)
                .nomeProduto("Produto Teste")
                .descricaoProduto("Descrição Teste")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(10)
                .build();

        when(gerenciarProdutoCasoDeUso.cadastrarNovaProduto(request)).thenReturn(mockProduto);

        // Act
        ResponseEntity<Produto> response = produtoController.cadastrarNovoProduto(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto Teste", response.getBody().getNomeProduto());
        assertEquals("Descrição Teste", response.getBody().getDescricaoProduto());
        verify(gerenciarProdutoCasoDeUso, times(1)).cadastrarNovaProduto(request);
    }

    @Test
    void listarTodasRemessas() {
        // Arrange
        List<Produto> mockProdutos = new ArrayList<>();
        mockProdutos.add(Produto.builder()
                .codigoProduto(1L)
                .nomeProduto("Produto 1")
                .descricaoProduto("Descrição 1")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(10)
                .build());
        mockProdutos.add(Produto.builder()
                .codigoProduto(2L)
                .nomeProduto("Produto 2")
                .descricaoProduto("Descrição 2")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(20)
                .build());

        when(gerenciarProdutoCasoDeUso.listaDeProdutos()).thenReturn(mockProdutos);

        // Act
        ResponseEntity<List<Produto>> response = ResponseEntity.ok(mockProdutos);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Produto 1", response.getBody().get(0).getNomeProduto());
        assertEquals("Produto 2", response.getBody().get(1).getNomeProduto());
//        verify(gerenciarProdutoCasoDeUso, times(1)).listaDeProdutos();
    }

    @Test
    void deveRetornarUmProdutoAoBuscarPorId() {
        // Arrange
        Long codigoProduto = 1L;

        Produto mockProduto = Produto.builder()
                .codigoProduto(codigoProduto)
                .nomeProduto("Produto Teste")
                .descricaoProduto("Descrição Teste")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(10)
                .build();

        when(gerenciarProdutoCasoDeUso.buscarProdutoPorId(codigoProduto)).thenReturn(mockProduto);

        // Act
        ResponseEntity<Produto> response = produtoController.buscarProdutoPorId(codigoProduto);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto Teste", response.getBody().getNomeProduto());
        verify(gerenciarProdutoCasoDeUso, times(1)).buscarProdutoPorId(codigoProduto);
    }

    @Test
    void atualizarProduto() {
        // Arrange
        Long codigoProduto = 1L;
        ProdutoRequest request = ProdutoRequest.builder()
                .nomeProduto("Produto Atualizado")
                .descricaoProduto("Descrição Atualizada")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(15)
                .build();

        RetornoServicoBase mockRetorno = RetornoServicoBase.positivo("Produto atualizado com sucesso!");

        when(gerenciarProdutoCasoDeUso.atualizarProdutoPorId(codigoProduto, request)).thenReturn(mockRetorno);

        // Act
        ResponseEntity<RetornoServicoBase> response = produtoController.atualizarProduto(codigoProduto, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto atualizado com sucesso!", response.getBody().getDescricao());
        verify(gerenciarProdutoCasoDeUso, times(1)).atualizarProdutoPorId(codigoProduto, request);
    }

    @Test
    void deletarProdutoPorId() {
        // Arrange
        Long codigoProduto = 1L;

        RetornoServicoBase mockRetorno = RetornoServicoBase.positivo("Produto deletado com sucesso!");

        when(gerenciarProdutoCasoDeUso.deletarProduto(codigoProduto)).thenReturn(mockRetorno);

        // Act
        ResponseEntity<RetornoServicoBase> response = produtoController.deletarProdutoPorId(codigoProduto);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto deletado com sucesso!", response.getBody().getDescricao());
        verify(gerenciarProdutoCasoDeUso, times(1)).deletarProduto(codigoProduto);
    }


}
