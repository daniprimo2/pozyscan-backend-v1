package com.gerenciador.frota.aplicacao.rh.adapters.outound.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoDocumento;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.DocumentosResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_DOCUMENTOS", schema = "sc_recursos_humanos")
public class DocumentosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_DOCUMENTO")
    private Long id;
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "DATA_EXPEDICAO")
    private String dataExpedicao;
    @Column(name = "DATA_VALIDADE")
    private String dataValidade;
    @Column(name = "ORGAO_EMISSOR")
    private String orgaoEmissor;
    @Lob
    @Column(name = "ARQUIVO")
    private String arquivoBase64;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_DOCUMENTO", unique = true)
    private TipoDocumento tipoDocumento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "COD_COLABORADOR")
    private ColaboradorEntity colaboradorEntity;

    public DocumentosEntity fromDocumento(DocumentosResponse documentosResponse) {
        this.id = documentosResponse.getId();
        this.numeroDocumento = documentosResponse.getNumeroDocumento();
        this.dataExpedicao = documentosResponse.getDataExpedicao();
        this.dataValidade = documentosResponse.getDataValidade();
        this.orgaoEmissor = documentosResponse.getOrgaoEmissor();
        this.arquivoBase64 = documentosResponse.getArquivoBase64();
        this.tipoDocumento = documentosResponse.getTipoDocumento();
        this.colaboradorEntity = null;
        return this;
    }

    public DocumentosEntity atualizarDocumento(DocumentosEntity documentosEntity) {
        this.numeroDocumento = documentosEntity.getNumeroDocumento();
        this.dataExpedicao = documentosEntity.getDataExpedicao();
        this.dataValidade = documentosEntity.getDataValidade();
        this.orgaoEmissor = documentosEntity.getOrgaoEmissor();
        this.arquivoBase64 = documentosEntity.getArquivoBase64();
        this.tipoDocumento = documentosEntity.getTipoDocumento();
        this.colaboradorEntity = documentosEntity.getColaboradorEntity();
        return this;
    }

    public DocumentosResponse construirDocumentoResponse() {
        return DocumentosResponse.builder()
                .id(this.id)
                .numeroDocumento(this.numeroDocumento)
                .dataExpedicao(this.dataExpedicao)
                .dataValidade(this.dataValidade)
                .orgaoEmissor(this.orgaoEmissor)
                .arquivoBase64(this.arquivoBase64)
                .tipoDocumento(this.tipoDocumento)
                .build();

    }
}
