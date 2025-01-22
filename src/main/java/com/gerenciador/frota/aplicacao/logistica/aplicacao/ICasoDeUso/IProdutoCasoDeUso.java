package com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;

import java.util.List;

public interface IProdutoCasoDeUso {

    Produto cadastrarNovaProduto(ProdutoRequest request);
    List<Produto> listaDeProdutos();

    Produto buscarProdutoPorId(Long id);

    RetornoServicoBase atualizarProdutoPorId(Long id, ProdutoRequest request);

    RetornoServicoBase deletarProduto(Long id);

}
