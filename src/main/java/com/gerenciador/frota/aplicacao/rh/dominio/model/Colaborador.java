package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;


import java.util.ArrayList;
import java.util.List;


public class Colaborador {


    private Long id;
    private String descricaoAtividade;
    private StatusColaborador status;
    private String dataContratacao;
    private String dataDemissao;

    private DadosPessoais dadosPessoais;


    private Cargo cargo;

    private List<Telefones> telefones = new ArrayList<>();


    private List<Email> emailS = new ArrayList<>();

    private List<Documentos> documentos = new ArrayList<>();

    public Colaborador(Long id, String descricaoAtividade, StatusColaborador status, String dataContratacao, String dataDemissao, DadosPessoais dadosPessoais, Cargo cargo, List<Telefones> telefones, List<Email> emailS, List<Documentos> documentos) {
        this.id = id;
        this.descricaoAtividade = descricaoAtividade;
        this.status = status;
        this.dataContratacao = dataContratacao;
        this.dataDemissao = dataDemissao;
        this.dadosPessoais = dadosPessoais;
        this.cargo = cargo;
        this.telefones = telefones;
        this.emailS = emailS;
        this.documentos = documentos;
    }

    public Colaborador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public StatusColaborador getStatus() {
        return status;
    }

    public void setStatus(StatusColaborador status) {
        this.status = status;
    }

    public String getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(String dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public String getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(String dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Telefones> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefones> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEmailS() {
        return emailS;
    }

    public void setEmailS(List<Email> emailS) {
        this.emailS = emailS;
    }

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }
}
