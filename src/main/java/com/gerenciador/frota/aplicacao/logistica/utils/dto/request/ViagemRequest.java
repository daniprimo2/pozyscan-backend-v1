package com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums.TipoViagem;
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

    public Viagem construirViagem() {
        return Viagem.builder()
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
