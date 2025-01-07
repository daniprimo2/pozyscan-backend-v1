package com.gerenciador.frota.aplicacao.rh.adapters.outound.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.EmailRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.EmailResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_EMAIL", schema = "sc_recursos_humanos")
public class EmailEmtity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_EMAIL")
    private Long id;
    @Column(name = "ENDERECO_EMAIL")
    private String email;
    @Column(name = "OBSERVACOES")
    private String observacoes;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CONTATO")
    private TipoContato tipoContato;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "COD_COLABORADOR")
    private ColaboradorEntity colaboradorEntity;

    public EmailEmtity fromEmail(EmailResponse email) {
        this.id = email.getId();
        this.email = email.getEmail();
        this.observacoes = email.getObservacoes();
        this.tipoContato = email.getTipoContato();
        this.colaboradorEntity = null;
        return this;
    }

    public EmailEmtity atualizarEmail(EmailRequest email) {
        this.email = email.getEmail();
        this.observacoes = email.getObservacoes();
        this.tipoContato = email.getTipoContato();
        return this;
    }

    public EmailEmtity atualizarEmail(Long codigoEmail, EmailEmtity emailEmtity) {
        this.id = codigoEmail;
        this.email = emailEmtity.getEmail();
        this.observacoes = emailEmtity.getObservacoes();
        this.tipoContato = emailEmtity.getTipoContato();
        return this;
    }

    public EmailResponse construirResponse() {
        return EmailResponse.builder()
                .id(this.id)
                .email(this.email)
                .observacoes(this.observacoes)
                .tipoContato(this.tipoContato)
                .build();
    }
}
