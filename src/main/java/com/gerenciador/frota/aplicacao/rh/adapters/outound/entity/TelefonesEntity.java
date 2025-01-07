package com.gerenciador.frota.aplicacao.rh.adapters.outound.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.TelefonesRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.TelefonesResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TELEFONE", schema = "sc_recursos_humanos")
public class TelefonesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TELEFONE")
    private Long id;
    @Column(name = "DD")
    private String dd;
    @Column(name = "NUMERO_TELEFONE")
    private String telefone;
    @Column(name = "OBSERVACOES")
    private String observacoes;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CONTATO")
    private TipoContato tipoContato;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "COD_COLABORADOR")
    private ColaboradorEntity colaboradorEntity;
    public TelefonesEntity fromTelefone(TelefonesResponse telefone) {
        this.id = telefone.getId();
        this.dd = telefone.getDd();
        this.telefone = telefone.getTelefone();
        this.observacoes = telefone.getObservacoes();
        this.tipoContato = telefone.getTipoContato();
        this.colaboradorEntity = null;
        return this;
    }

    public TelefonesEntity atualizarTelefone(Long codigoTelefone, TelefonesEntity telefone) {
        this.id = codigoTelefone;
        this.dd = telefone.getDd();
        this.telefone = telefone.getTelefone();
        this.observacoes = telefone.getObservacoes();
        this.tipoContato = telefone.getTipoContato();
        this.colaboradorEntity = telefone.getColaboradorEntity();
        return this;

    }

    public TelefonesEntity atualizarTelefone(TelefonesRequest telefonesRequest) {
        this.dd = telefonesRequest.getDd();
        this.telefone = telefonesRequest.getTelefone();
        this.observacoes = telefonesRequest.getObservacoes();
        this.tipoContato = telefonesRequest.getTipoContato();
        return this;
    }

    public TelefonesResponse construirResponse() {
        return TelefonesResponse.builder()
                .id(this.id)
                .dd(this.dd)
                .telefone(this.telefone)
                .observacoes(this.observacoes)
                .tipoContato(this.tipoContato)
                .build();
    }
}
