package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.EnderecoRequest;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotaFiscalLogisticaRequest {

    private String numeroNotaFisal;
    private Double valorTotal;
    private String dataEmissao;
    private EnderecoRequest  enderecoRequest;

}
