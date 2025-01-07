package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;


public class Documentos {

    private Long id;
    private String numeroDocumento;
    private String dataExpedicao;
    private String dataValidade;
    private String orgaoEmissor;
    private String arquivoBase64;

    private TipoDocumento tipoDocumento;

    private Colaborador colaborador;


    public Documentos(Long id, String numeroDocumento, String dataExpedicao, String dataValidade, String orgaoEmissor, String arquivoBase64, TipoDocumento tipoDocumento, Colaborador colaborador) {
        this.id = id;
        this.numeroDocumento = numeroDocumento;
        this.dataExpedicao = dataExpedicao;
        this.dataValidade = dataValidade;
        this.orgaoEmissor = orgaoEmissor;
        this.arquivoBase64 = arquivoBase64;
        this.tipoDocumento = tipoDocumento;
        this.colaborador = colaborador;
    }

    public Documentos() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(String dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public String getArquivoBase64() {
        return arquivoBase64;
    }

    public void setArquivoBase64(String arquivoBase64) {
        this.arquivoBase64 = arquivoBase64;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
