package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaProdutoEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaProdutoRepository;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Produto;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.ProdutoRepositoryPort;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ProdutoRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class ProdutoRepositoryImplementacao implements ProdutoRepositoryPort {

    private final JpaProdutoRepository produtoRepository;




    @Override
    public Produto cadastrarProduto(ProdutoRequest request) {
        log.info("[START] - Cadastrar Produto no Banco de Dados");
        try {
            var produtoSalvo = produtoRepository.save(Mappers.fromProdutoRequestToProduto(request));
            log.info("[END] - Produto Salvo com sucesso de ID: {}", produtoSalvo.getCodigoProduto());
            return Mappers.fromJpaProdutoEntityToProduto(produtoSalvo);
        } catch (Exception e) {
            log.info("[ERRO] - Erro ao cadastrar produto de NOME: {}", request.getNomeProduto());
            throw new RuntimeException("Falha ao cadastrar produto.");
        }
    }

    @Override
    public List<Produto> listarProdutos() {
        log.info("[START] - Buscar Lista de todos os produtos.");
        try {
            List<JpaProdutoEntity> todosProdutos = produtoRepository.findAll();
            log.info("[INFO] - Trouxe todos os {} produtos.", todosProdutos.size());
            List<Produto> produtos = Mappers.fromListJpaProdutoEntityToListProduto(todosProdutos);
            log.info("[INFO] - Converteu lista de entities produto para o model produto.");
            return produtos;
        } catch (NoSuchMethodException ex) {
            log.info("[ERRO] - Deu erro ao converter a lista de produtos entities para produto.");
            throw new RuntimeException(ex.getMessage());
        } catch (Exception ex) {
            log.info("[ERRO] - Deu erro ao buscar as listas.");
            throw new RuntimeException("Erro ao buscar toda lista.");
        }
    }

    @Override
    public Produto buscarProdutoPorCodigo(Long codigoProduto) {
            log.info("[START] - Buscar o produto de codigo: {}.", codigoProduto);
            var produto = produtoRepository.findById(codigoProduto)
                    .map(Mappers::fromComCodigoJpaProdutoEntityToProduto)
                    .orElseThrow(() -> {
                        log.info("[ERRO] - Erro ao buscar o produto por codigo {}.", codigoProduto);
                        return new RuntimeException("Produto não encontrado.");
                    });
            log.info("[END] - Produto {} encontrado com sucesso.", codigoProduto);
            return produto;
    }

    @Override
    public RetornoServicoBase atuallizarProdutoPorCodigo(Long codigoProduto, ProdutoRequest request) {
        try {
            log.info("[START] - Atualizar produto.");
            Produto produto = this.buscarProdutoPorCodigo(codigoProduto);
            JpaProdutoEntity produtoAtualizadoEntity = this.atualizarProduto(produto, request);
            log.info("[INFO] - Produto enity atualizado, pronto para salvar.");
            this.salvarProduto(produtoAtualizadoEntity);
            return RetornoServicoBase.positivo("Produto de codigo " + codigoProduto + " atualizado com sucesso.");
        } catch (Exception ex) {
            log.info("[ERRO] - Produto nao atualziado.");
            return RetornoServicoBase.negativo("Erro ao atualziar o produto de codigo " + codigoProduto);
        }
    }

    private JpaProdutoEntity salvarProduto(JpaProdutoEntity produtoAtualizadoEntity) {
        log.info("[START] - Atualizar Produto no Banco de Dados");
        try {
            var produtoSalvo = produtoRepository.save(produtoAtualizadoEntity);
            log.info("[END] - Produto de ID: {} atualizado com sucesso.", produtoSalvo.getCodigoProduto());
            return produtoSalvo;
        } catch (Exception e) {
            log.info("[ERRO] - Erro ao atualizar produto de id: {}", produtoAtualizadoEntity.getCodigoProduto());
            throw new RuntimeException("Falha ao atualizar produto.");
        }

    }

    private JpaProdutoEntity atualizarProduto(Produto produto, ProdutoRequest request) {
        produto.setNomeProduto(request.getNomeProduto());
        produto.setDescricaoProduto(request.getDescricaoProduto());
        produto.setTipoProduto(request.getTipoProduto());
        produto.setQuantidade(request.getQuantidade());
        produto.setPesoLiquido(request.getPesoProdutoRequest().getPesoLiquido());
        produto.setPesoBruto(request.getPesoProdutoRequest().getPesoBruto());
        produto.setAltura(request.getDimensaoProdutoRequest().getAltura());
        produto.setLargura(request.getDimensaoProdutoRequest().getLargura());
        produto.setValorLiquido(request.getPrecoProdutoRequest().getValorLiquido());
        produto.setValorBruto(request.getPrecoProdutoRequest().getValorBruto());
        return Mappers.fromComCodigoProdutoRequestToProduto(produto);
    }

    @Override
    public RetornoServicoBase deletarProdutoPorCodigo(Long codigoProduto) {
        try {
            log.info("[START] - Deletar produto de id: {}", codigoProduto);
            Produto produto = this.buscarProdutoPorCodigo(codigoProduto);
            produtoRepository.delete(Mappers.fromComCodigoProdutoRequestToProduto(produto));
            log.info("[END] - Sucesso ao deletar o produto de id: {}", codigoProduto);
            return RetornoServicoBase.positivo("Produto " + produto.getNomeProduto() + " deletado com sucesso.");
        } catch (Exception ex) {
            log.info("[ERRO] - Erro ao deletar o produto de id: {}", codigoProduto);
            return RetornoServicoBase.negativo("Erro ao deletar o produto de id " + codigoProduto + ".");
        }

    }
}
