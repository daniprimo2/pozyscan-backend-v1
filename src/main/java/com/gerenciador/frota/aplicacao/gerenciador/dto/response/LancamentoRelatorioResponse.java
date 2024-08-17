package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

public interface LancamentoRelatorioResponse {


    Long getIdLancamento();

    String getCentroDeCusto();

    String getNumeroNf();

    String getDataEmissao();

    String getFormaPagamento();

    String getValorTotal();

    String getStatusNotaFiscal();

    Long getParcelasAPagar();

    Long getParcelasPagas();

    String getVencProxParcela();

    String getStatusParcela();

    String getValorParcela();

}
