package com.gerenciador.frota.aplicacao.logistica.dominio.model;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;

public class NotaFiscalLogistica {

    private Long codigoNotaFiscal;

    private String numeroNotaFisal;

    private Double valorTotal;

    private String dataEmissao;

    private Remessa remessa;

    private Endereco endereco;

    public NotaFiscalLogistica() {
    }

    public NotaFiscalLogistica(String numeroNotaFisal, Double valorTotal, String dataEmissao, Remessa remessa, Endereco endereco) {
        this.numeroNotaFisal = numeroNotaFisal;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;
        this.remessa = remessa;
        this.endereco = endereco;
    }

    public NotaFiscalLogistica(Long codigoNotaFiscal, String numeroNotaFisal, Double valorTotal, String dataEmissao, Remessa remessa, Endereco endereco) {
        this.codigoNotaFiscal = codigoNotaFiscal;
        this.numeroNotaFisal = numeroNotaFisal;
        this.valorTotal = valorTotal;
        this.dataEmissao = dataEmissao;
        this.remessa = remessa;
        this.endereco = endereco;
    }

    public Long getCodigoNotaFiscal() {
        return codigoNotaFiscal;
    }

    public void setCodigoNotaFiscal(Long codigoNotaFiscal) {
        this.codigoNotaFiscal = codigoNotaFiscal;
    }

    public String getNumeroNotaFisal() {
        return numeroNotaFisal;
    }

    public void setNumeroNotaFisal(String numeroNotaFisal) {
        this.numeroNotaFisal = numeroNotaFisal;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Remessa getRemessa() {
        return remessa;
    }

    public void setRemessa(Remessa remessa) {
        this.remessa = remessa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
