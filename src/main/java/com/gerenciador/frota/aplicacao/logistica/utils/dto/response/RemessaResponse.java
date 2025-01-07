package com.gerenciador.frota.aplicacao.logistica.utils.dto.response;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import lombok.Builder;

@Builder
public class RemessaResponse {

    private Long id;

    private String cliente;

    private String dataCriacao;

    private Double volumeTotal;

    private Double pesoTotal;

    private StatusRemessa statusRemessa;

    public RemessaResponse() {
    }

    public RemessaResponse(Long id, String cliente, String dataCriacao, Double volumeTotal, Double pesoTotal, StatusRemessa statusRemessa) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.volumeTotal = volumeTotal;
        this.pesoTotal = pesoTotal;
        this.statusRemessa = statusRemessa;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Double getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Double volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public Double getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(Double pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public StatusRemessa getStatusRemessa() {
        return statusRemessa;
    }

    public void setStatusRemessa(StatusRemessa statusRemessa) {
        this.statusRemessa = statusRemessa;
    }
}
