package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities;

import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_VIAGEM", schema = "sc_logistica")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_viagem")
    private Long id;

    @Column(name = "data_criacao")
    private String dataCriacao;

    @Column(name = "data_programada_viagem")
    private String dataProgramadaViagem;

    @Column(name = "data_realziado_viagem")
    private String dataRealizadoViagem;

    @Column(name = "volume_total")
    private Double volumeTotal;

    @Column(name = "peso_total")
    private Double pesoTotal;

    @Column(name = "total_kilometragem")
    private Double totalKilometragem;

    @Column(name = "total_remessa")
    private Double totalRemessa;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_viagem")
    private TipoViagem tipoViagem;

    @OneToMany(mappedBy = "viagem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Remessa> remessas = new ArrayList<>();

}
