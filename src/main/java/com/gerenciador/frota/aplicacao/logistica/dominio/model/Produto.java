package com.gerenciador.frota.aplicacao.logistica.dominio.model;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;


public class Produto {

    private Long codigoProduto;

    private String nomeProduto;

    private String descricaoProduto;

    private TipoProduto tipoProduto;

    private Double pesoLiquido;

    private Double pesoBruto;

    private int quantidade;

    private Double valorLiquido;


    private Double valorBruto;

    private Double largura;

    private Double altura;

    private Double comprimento;

    private NotaFiscalLogistica notaFiscalLogistica;

    public Produto() {
    }

    public Produto(String nomeProduto, String descricaoProduto, TipoProduto tipoProduto, Double pesoLiquido, Double pesoBruto, int quantidade, Double valorLiquido, Double valorBruto, Double largura, Double altura, Double comprimento, NotaFiscalLogistica notaFiscalLogistica) {
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.tipoProduto = tipoProduto;
        this.pesoLiquido = pesoLiquido;
        this.pesoBruto = pesoBruto;
        this.quantidade = quantidade;
        this.valorLiquido = valorLiquido;
        this.valorBruto = valorBruto;
        this.largura = largura;
        this.altura = altura;
        this.comprimento = comprimento;
        this.notaFiscalLogistica = notaFiscalLogistica;
    }

    public Produto(Long codigoProduto, String nomeProduto, String descricaoProduto, TipoProduto tipoProduto, Double pesoLiquido, Double pesoBruto, int quantidade, Double valorLiquido, Double valorBruto, Double largura, Double altura, Double comprimento, NotaFiscalLogistica notaFiscalLogistica) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.tipoProduto = tipoProduto;
        this.pesoLiquido = pesoLiquido;
        this.pesoBruto = pesoBruto;
        this.quantidade = quantidade;
        this.valorLiquido = valorLiquido;
        this.valorBruto = valorBruto;
        this.largura = largura;
        this.altura = altura;
        this.comprimento = comprimento;
        this.notaFiscalLogistica = notaFiscalLogistica;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Double getPesoLiquido() {
        return pesoLiquido;
    }

    public void setPesoLiquido(Double pesoLiquido) {
        this.pesoLiquido = pesoLiquido;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public Double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(Double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getComprimento() {
        return comprimento;
    }

    public void setComprimento(Double comprimento) {
        this.comprimento = comprimento;
    }

    public NotaFiscalLogistica getNotaFiscalLogistica() {
        return notaFiscalLogistica;
    }

    public void setNotaFiscalLogistica(NotaFiscalLogistica notaFiscalLogistica) {
        this.notaFiscalLogistica = notaFiscalLogistica;
    }
}
