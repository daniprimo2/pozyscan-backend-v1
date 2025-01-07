package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotaFiscalLogisticaRequest {

    private String numeroNotaFisal;


    public JpaNotaFiscalLogisticaEntity construirNotaFiscal() {
        return JpaNotaFiscalLogisticaEntity.builder()
                .numeroNotaFisal(this.numeroNotaFisal)
                .build();
    }
}
