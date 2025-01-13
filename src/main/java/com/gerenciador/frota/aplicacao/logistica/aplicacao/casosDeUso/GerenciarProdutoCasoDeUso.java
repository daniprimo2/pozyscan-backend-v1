package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.ProdutoRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso.IProdutoCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class GerenciarProdutoCasoDeUso implements IProdutoCasoDeUso {


    private final ProdutoRepositoryImplementacao produtoRepositoryImplementacao;

    @Override
    public Produto cadastrarNovaProduto(ProdutoRequest request) {
        return produtoRepositoryImplementacao.cadastrarProduto(request);
    }

    @Override
    public List<Produto> listaDeProdutos() {
        return produtoRepositoryImplementacao.listarProdutos();
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        return produtoRepositoryImplementacao.buscarProdutoPorCodigo(id);
    }

    @Override
    public RetornoServicoBase atualizarProdutoPorId(Long id, ProdutoRequest request) {
        return produtoRepositoryImplementacao.atuallizarProdutoPorCodigo(id, request);
    }

    @Override
    public RetornoServicoBase deletarProduto(Long id) {
        return produtoRepositoryImplementacao.deletarProdutoPorCodigo(id);
    }
}
