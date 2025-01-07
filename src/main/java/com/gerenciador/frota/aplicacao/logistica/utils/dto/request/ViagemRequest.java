package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ViagemRequest {

    private LocalDate dataViagem;
    private TipoViagem tipoViagem;

    public JpaViagemEntity construirViagem() {
        return JpaViagemEntity.builder()
                .dataCriacao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .tipoViagem(this.tipoViagem)
                .dataProgramadaViagem(DataUtils.converterLocalDateParaNossoPadraoData(this.dataViagem))
                .volumeTotal(0.0)
                .pesoTotal(0.0)
                .totalKilometragem(0.0)
                .totalRemessa(0.0)
                .build();
    }
}
