package com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.ProdutoResponse;

import java.util.List;

public interface ProdutoRepositoryPort {


    Produto cadastrarProduto(ProdutoRequest request);
    List<Produto> listarProdutos();
    Produto buscarProdutoPorCodigo(Long codigoProduto);

    RetornoServicoBase atuallizarProdutoPorCodigo(Long codigoProduto, ProdutoRequest request);
    RetornoServicoBase deletarProdutoPorCodigo(Long codigoProduto);

}
