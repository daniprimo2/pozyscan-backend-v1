package com.gerenciador.frota.aplicacao.rh.dominio.model;


import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;



public class Email {

    private Long id;
    private String email;
    private String observacoes;
    private TipoContato tipoContato;


    private Colaborador colaborador;


    public Email(Long id, String email, String observacoes, TipoContato tipoContato, Colaborador colaborador) {
        this.id = id;
        this.email = email;
        this.observacoes = observacoes;
        this.tipoContato = tipoContato;
        this.colaborador = colaborador;
    }

    public Email() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
