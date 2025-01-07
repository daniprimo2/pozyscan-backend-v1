package com.gerenciador.frota.aplicacao.rh.dominio.model;


import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;



public class Telefones {

    private Long id;
    private String dd;
    private String telefone;
    private String observacoes;
    private TipoContato tipoContato;

    private Colaborador colaborador;

    public Telefones(Long id, String dd, String telefone, String observacoes, TipoContato tipoContato, Colaborador colaborador) {
        this.id = id;
        this.dd = dd;
        this.telefone = telefone;
        this.observacoes = observacoes;
        this.tipoContato = tipoContato;
        this.colaborador = colaborador;
    }

    public Telefones() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
}
