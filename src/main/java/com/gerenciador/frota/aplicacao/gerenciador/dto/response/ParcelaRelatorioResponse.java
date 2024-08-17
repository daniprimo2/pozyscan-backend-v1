package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

public interface ParcelaRelatorioResponse {

    Long getIdLancamento();
    String getCentroDeCusto();
    String getNotaFiscalNumeroNf();
    String getDataVencimento();
    String getDescricaoParcela();
    String getStatusPagamento();
    String getValorParcela();


}
