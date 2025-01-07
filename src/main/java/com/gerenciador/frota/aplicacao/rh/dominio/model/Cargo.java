package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoCargo;


public class Cargo {

    private Long id;
    private String nomeCargo;
    private String descricaoCargo;
    private TipoCargo tipoCargo;


    public Cargo(Long id, String nomeCargo, String descricaoCargo, TipoCargo tipoCargo) {
        this.id = id;
        this.nomeCargo = nomeCargo;
        this.descricaoCargo = descricaoCargo;
        this.tipoCargo = tipoCargo;
    }


    public Cargo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public String getDescricaoCargo() {
        return descricaoCargo;
    }

    public void setDescricaoCargo(String descricaoCargo) {
        this.descricaoCargo = descricaoCargo;
    }

    public TipoCargo getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(TipoCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }
}
