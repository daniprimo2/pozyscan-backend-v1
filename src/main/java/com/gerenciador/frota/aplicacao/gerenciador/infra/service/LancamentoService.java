package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.StatusLancamento;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.LancamentoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectsResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.LancamentoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private AplicacaoService aplicacaoService;
    @Autowired
    private FilialService filialService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private NotaFiscalService notaFiscalService;


    public RetornoServicoBase registrarNovoLancamento(LancamentoRequest lancamentoRequest) {
        Aplicacao aplicacao = aplicacaoService.buscarAplicacaoPorId(lancamentoRequest.getAplicacaoId());
        Filial filial = filialService.buscarFilialPorId(lancamentoRequest.getFilialId());
        Veiculo veiculo = veiculoService.buscarVeiculoPorPlaca(lancamentoRequest.getPlacaVeiculo());
        Fornecedor fornecedor = fornecedorService.buscarFornecedorPorId(lancamentoRequest.getFornecedorId());
        NotaFiscal notaFiscal1 = notaFiscalService.buscarNFPorId(lancamentoRequest.getNotaFiscalId());
        Lancamento lancamento = Lancamento.builder()
                .data(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .aplicacao_id(aplicacao)
                .filial_id(filial)
                .veiculo_id(veiculo)
                .fornecedor_id(fornecedor)
                .numero_nf(notaFiscal1)
                .status(StatusLancamento.AGUARDANDO_FATURAMENTO)
                .centroCusto(lancamentoRequest.getCentroCusto())
                .build();
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        return RetornoServicoBase.positivo("Foi registrado o lancamento de numero: "+ lancamentoSalvo.getId());
    }

    public SelectsResponse buscarSelects() {

        return SelectsResponse.builder()
                .placas(veiculoService.buscarTodosVeiculos().stream().map(Veiculo::tratandoSelectsPlaca).toList())
                .aplicacoes(aplicacaoService.buscarTodasAplicacoes().stream().map(Aplicacao::tratarSelectsAplicacoes).toList())
                .filiais(filialService.buscarTodasFiliais().stream().map(Filial::tratarSelectsFiliais).toList())
                .fornecedores(fornecedorService.buscarTodosFornecedores().stream().map(Fornecedor::tratarSelectsFornecedores).toList())
                .build();
    }
}
