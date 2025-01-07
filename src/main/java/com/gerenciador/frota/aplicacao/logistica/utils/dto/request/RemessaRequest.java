package com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums.StatusRemessa;
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

    public Remessa construirRemessa() {
        return Remessa.builder()
                .statusRemessa(StatusRemessa.VAZIA)
                .dataCriacao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .cliente(this.cliente)
                .build();
    }
}
