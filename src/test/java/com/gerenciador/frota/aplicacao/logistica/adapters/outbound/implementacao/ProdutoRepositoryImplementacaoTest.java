package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaProdutoEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaProdutoRepository;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.DimensaoProdutoRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.PesoProdutoRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.PrecoProdutoRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoRepositoryImplementacaoTest {

    @Mock
    private JpaProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoRepositoryImplementacao produtoRepositoryImplementacao;

    private Produto produto;

    private PesoProdutoRequest pesoProdutoRequest;

    private DimensaoProdutoRequest dimensaoProdutoRequest;

    private PrecoProdutoRequest precoProdutoRequest;

    private NotaFiscalLogistica notaFiscalLogistica;

    private Remessa remessa;
    private Endereco endereco;

    private JpaProdutoEntity jpaProdutoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        remessa = new Remessa(
                1L,
                "Cliente A",
                "2025-01-07",
                10.0,
                20.0,
                StatusRemessa.VAZIA);

        endereco = new Endereco(
                6l,
                "06385820",
                "Rua Ipixuna",
                "154",
                "Vila Menk",
                "Carapicuiba",
                "SP",
                "São Paulo",
                "");

        this.notaFiscalLogistica = new NotaFiscalLogistica(
                2525l,
                "010101",
                5.0,
                "15/05/2025",
                remessa,
                endereco
        );

        produto = new Produto(
                1l,
                "Produto Teste",
                "O Produto 01",
                TipoProduto.UNIDADE,
                1.0,
                1.0,
                10,
                1.0,
                1.0,
                1.0,
                2.0,
                1.0,
                this.notaFiscalLogistica);

        pesoProdutoRequest = PesoProdutoRequest.builder()
                .pesoLiquido(1.0)
                .pesoBruto(1.0)
                .build();

        dimensaoProdutoRequest = DimensaoProdutoRequest.builder()
                .altura(1.0)
                .largura(1.0)
                .comprimento(2.0)
                .build();

        precoProdutoRequest = PrecoProdutoRequest.builder()
                .valorLiquido(25.00)
                .valorBruto(50.00)
                .build();


        jpaProdutoEntity = Mappers.fromComCodigoProdutoRequestToProduto(this.produto);
    }

    @Test
    void cadastrarProduto() {

        ProdutoRequest request = ProdutoRequest.builder()
                .nomeProduto("Produto Teste")
                .descricaoProduto("Descrição Teste")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(10)
                .pesoProdutoRequest(this.pesoProdutoRequest)
                .dimensaoProdutoRequest(this.dimensaoProdutoRequest)
                .precoProdutoRequest(this.precoProdutoRequest)
                .build();

        JpaProdutoEntity mockEntity = Mappers.fromProdutoRequestToProduto(request);
        when(produtoRepository.save(any(JpaProdutoEntity.class))).thenReturn(Mappers.fromProdutoRequestToProduto(this.produto));

        Produto result = produtoRepositoryImplementacao.cadastrarProduto(request);

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNomeProduto());
        verify(produtoRepository, times(1)).save(any(JpaProdutoEntity.class));
    }

    @Test
    void listarProdutos() {
        JpaProdutoEntity mockEntity = new JpaProdutoEntity();
        mockEntity.setNomeProduto("Produto Teste");
        when(produtoRepository.findAll()).thenReturn(Collections.singletonList(mockEntity));

        var result = produtoRepositoryImplementacao.listarProdutos();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void buscarProdutoPorCodigo() {
        Long codigoProduto = 1L;
        JpaProdutoEntity mockEntity = new JpaProdutoEntity();
        mockEntity.setCodigoProduto(codigoProduto);

        when(produtoRepository.findById(codigoProduto)).thenReturn(Optional.of(Mappers.fromComCodigoProdutoRequestToProduto(this.produto)));


        Produto result = produtoRepositoryImplementacao.buscarProdutoPorCodigo(codigoProduto);
        assertNotNull(result);
        assertEquals(1l, result.getCodigoProduto());
        assertEquals("Produto Teste", result.getNomeProduto());
        verify(produtoRepository, times(1)).findById(codigoProduto);
    }

    @Test
    void atuallizarProdutoPorCodigo() {
        Long codigoProduto = 1L;
        ProdutoRequest request = ProdutoRequest.builder()
                .nomeProduto("Produto Atualizado")
                .descricaoProduto("Descrição Atualizada")
                .tipoProduto(TipoProduto.UNIDADE)
                .quantidade(5)
                .pesoProdutoRequest(this.pesoProdutoRequest)
                .dimensaoProdutoRequest(this.dimensaoProdutoRequest)
                .precoProdutoRequest(this.precoProdutoRequest)
                .build();

        JpaProdutoEntity mockEntity = new JpaProdutoEntity();
        mockEntity.setCodigoProduto(codigoProduto);
        jpaProdutoEntity.setCodigoProduto(1l);
        when(produtoRepository.findById(codigoProduto)).thenReturn(Optional.of(jpaProdutoEntity));
        when(produtoRepository.save(any(JpaProdutoEntity.class))).thenReturn(jpaProdutoEntity);

        var result = produtoRepositoryImplementacao.atuallizarProdutoPorCodigo(codigoProduto, request);

        assertTrue(result.getFuncionou());
        assertEquals("Produto de codigo 1 atualizado com sucesso.", result.getDescricao());
        verify(produtoRepository, times(1)).findById(codigoProduto);
        verify(produtoRepository, times(1)).save(any(JpaProdutoEntity.class));
    }

    @Test
    void deletarProdutoPorCodigo() {
        Long codigoProduto = 1L;
        JpaProdutoEntity mockEntity = new JpaProdutoEntity();
        mockEntity.setCodigoProduto(codigoProduto);

        when(produtoRepository.findById(codigoProduto)).thenReturn(Optional.of(mockEntity));

        var result = produtoRepositoryImplementacao.deletarProdutoPorCodigo(codigoProduto);

        assertTrue(result.getFuncionou());
        assertEquals("Produto null deletado com sucesso.", result.getDescricao()); // "null" porque `mockEntity.getNomeProduto()` não foi configurado
        verify(produtoRepository, times(1)).findById(codigoProduto);
        verify(produtoRepository, times(1)).delete(any(JpaProdutoEntity.class));
    }
}
