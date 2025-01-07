package com.gerenciador.frota.aplicacao.logistica.dominio.model;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;

import java.util.ArrayList;
import java.util.List;

public class Remessa {

    private Long id;

    private String cliente;

    private String dataCriacao;

    private Double volumeTotal;

    private Double pesoTotal;

    private StatusRemessa statusRemessa;

    private Viagem viagem;

    private List<NotaFiscalLogistica> notaFiscalLogisticas = new ArrayList<>();

    public Remessa() {
    }

    public Remessa(String cliente, String dataCriacao, Double volumeTotal, Double pesoTotal, StatusRemessa statusRemessa, Viagem viagem, List<NotaFiscalLogistica> notaFiscalLogisticas) {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.volumeTotal = volumeTotal;
        this.pesoTotal = pesoTotal;
        this.statusRemessa = statusRemessa;
        this.viagem = viagem;
        this.notaFiscalLogisticas = notaFiscalLogisticas;
    }

    public Remessa(Long id, String cliente, String dataCriacao, Double volumeTotal, Double pesoTotal, StatusRemessa statusRemessa, Viagem viagem, List<NotaFiscalLogistica> notaFiscalLogisticas) {
        this.id = id;
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.volumeTotal = volumeTotal;
        this.pesoTotal = pesoTotal;
        this.statusRemessa = statusRemessa;
        this.viagem = viagem;
        this.notaFiscalLogisticas = notaFiscalLogisticas;
    }

    public Remessa(Long id, String cliente, String dataCriacao, Double volumeTotal, Double pesoTotal, StatusRemessa statusRemessa) {
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

    public Viagem getViagem() {
        return viagem;
    }


    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public List<NotaFiscalLogistica> getNotaFiscalLogisticas() {
        return notaFiscalLogisticas;
    }

    public void setNotaFiscalLogisticas(List<NotaFiscalLogistica> notaFiscalLogisticas) {
        this.notaFiscalLogisticas = notaFiscalLogisticas;
    }

}
