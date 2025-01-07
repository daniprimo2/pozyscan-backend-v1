package com.gerenciador.frota.aplicacao.logistica.dominio.model;

import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;

import java.util.ArrayList;
import java.util.List;


public class Viagem {

    private Long id;

    private String dataCriacao;

    private String dataProgramadaViagem;

    private String dataRealizadoViagem;

    private Double volumeTotal;

    private Double pesoTotal;

    private Double totalKilometragem;

    private Double totalRemessa;

    private Veiculo veiculo;

    private TipoViagem tipoViagem;

    private List<Remessa> remessas = new ArrayList<>();


    public Viagem() {
    }

    public Viagem(Long id, String dataCriacao, String dataProgramadaViagem, String dataRealizadoViagem, Double volumeTotal, Double pesoTotal, Double totalKilometragem, Double totalRemessa, Veiculo veiculo, TipoViagem tipoViagem, List<Remessa> remessas) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataProgramadaViagem = dataProgramadaViagem;
        this.dataRealizadoViagem = dataRealizadoViagem;
        this.volumeTotal = volumeTotal;
        this.pesoTotal = pesoTotal;
        this.totalKilometragem = totalKilometragem;
        this.totalRemessa = totalRemessa;
        this.veiculo = veiculo;
        this.tipoViagem = tipoViagem;
        this.remessas = remessas;
    }

    public Viagem(String dataCriacao, String dataProgramadaViagem, String dataRealizadoViagem, Double volumeTotal, Double pesoTotal, Double totalKilometragem, Double totalRemessa, Veiculo veiculo, TipoViagem tipoViagem, List<Remessa> remessas) {
        this.dataCriacao = dataCriacao;
        this.dataProgramadaViagem = dataProgramadaViagem;
        this.dataRealizadoViagem = dataRealizadoViagem;
        this.volumeTotal = volumeTotal;
        this.pesoTotal = pesoTotal;
        this.totalKilometragem = totalKilometragem;
        this.totalRemessa = totalRemessa;
        this.veiculo = veiculo;
        this.tipoViagem = tipoViagem;
        this.remessas = remessas;
    }

    public Viagem(Long id, String dataCriacao, String dataProgramadaViagem, String dataRealizadoViagem, Double volumeTotal, Double pesoTotal, Double totalKilometragem, Double totalRemessa, Veiculo veiculo, TipoViagem tipoViagem) {
        this.dataCriacao = dataCriacao;
        this.dataProgramadaViagem = dataProgramadaViagem;
        this.dataRealizadoViagem = dataRealizadoViagem;
        this.volumeTotal = volumeTotal;
        this.pesoTotal = pesoTotal;
        this.totalKilometragem = totalKilometragem;
        this.totalRemessa = totalRemessa;
        this.veiculo = veiculo;
        this.tipoViagem = tipoViagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataProgramadaViagem() {
        return dataProgramadaViagem;
    }

    public void setDataProgramadaViagem(String dataProgramadaViagem) {
        this.dataProgramadaViagem = dataProgramadaViagem;
    }

    public String getDataRealizadoViagem() {
        return dataRealizadoViagem;
    }

    public void setDataRealizadoViagem(String dataRealizadoViagem) {
        this.dataRealizadoViagem = dataRealizadoViagem;
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

    public Double getTotalKilometragem() {
        return totalKilometragem;
    }

    public void setTotalKilometragem(Double totalKilometragem) {
        this.totalKilometragem = totalKilometragem;
    }

    public Double getTotalRemessa() {
        return totalRemessa;
    }

    public void setTotalRemessa(Double totalRemessa) {
        this.totalRemessa = totalRemessa;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public TipoViagem getTipoViagem() {
        return tipoViagem;
    }

    public void setTipoViagem(TipoViagem tipoViagem) {
        this.tipoViagem = tipoViagem;
    }

    public List<Remessa> getRemessas() {
        return remessas;
    }

    public void setRemessas(List<Remessa> remessas) {
        this.remessas = remessas;
    }
}
