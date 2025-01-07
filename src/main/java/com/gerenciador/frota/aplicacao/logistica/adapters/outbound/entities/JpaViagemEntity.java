package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities;

import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
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
public class JpaViagemEntity {

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

    @OneToMany(mappedBy = "jpaViagemEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JpaRemessaEntity> JpaRemessaEntityEntities = new ArrayList<>();

    public Viagem getViagem() {
        return montarViagem();
    }

    private Viagem montarViagem() {
        Viagem viagem = new Viagem();
        viagem.setId(this.id);
        viagem.setDataCriacao(this.dataCriacao);
        viagem.setDataProgramadaViagem(this.dataProgramadaViagem);
        viagem.setDataRealizadoViagem(this.dataRealizadoViagem);
        viagem.setVolumeTotal(this.volumeTotal);
        viagem.setPesoTotal(this.pesoTotal);
        viagem.setTotalKilometragem(this.totalKilometragem);
        viagem.setTotalRemessa(this.totalRemessa);
        viagem.setVeiculo(this.veiculo);
        viagem.setTipoViagem(this.tipoViagem);
        viagem.setRemessas(this.JpaRemessaEntityEntities != null && !this.JpaRemessaEntityEntities.isEmpty() ?
                this.JpaRemessaEntityEntities.stream().map(Mappers::fromJpaRemessaEntityToRemessa).toList() : new ArrayList<>());
        return viagem;
    }
}
