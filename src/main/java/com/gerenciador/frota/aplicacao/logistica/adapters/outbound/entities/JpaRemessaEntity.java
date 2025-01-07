package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_REMESSA", schema = "sc_logistica")
public class JpaRemessaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_remessa")
    private Long id;

    @Column(name = "nome_cliente")
    private String cliente;

    @Column(name = "data_criacao")
    private String dataCriacao;

    @Column(name = "volume_total")
    private Double volumeTotal;

    @Column(name = "peso_total")
    private Double pesoTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_remessa")
    private StatusRemessa statusRemessa;

    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private JpaViagemEntity jpaViagemEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "remessa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JpaNotaFiscalLogisticaEntity> jpaNotaFiscalLogisticaEntities = new ArrayList<>();

    public JpaRemessaEntity atualizarRemessa(RemessaRequest request) {
        this.cliente = request.getCliente();
        return this;
    }
}
