package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RemessaRequest {

    private String cliente;

    public JpaRemessaEntity construirRemessa() {
        return JpaRemessaEntity.builder()
                .statusRemessa(StatusRemessa.VAZIA)
                .dataCriacao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .cliente(this.cliente)
                .build();
    }
}
