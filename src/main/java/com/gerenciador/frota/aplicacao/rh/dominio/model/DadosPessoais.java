package com.gerenciador.frota.aplicacao.rh.dominio.model;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import jakarta.persistence.*;
import lombok.*;


public class DadosPessoais {

    private Long id;
    private String nomeCompleto;
    private String dataNascimento;
    private String nomeMae;
    private String nomePai;

    private Endereco endereco;

    public DadosPessoais(Long id, String nomeCompleto, String dataNascimento, String nomeMae, String nomePai, Endereco endereco) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.endereco = endereco;
    }

    public DadosPessoais() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
