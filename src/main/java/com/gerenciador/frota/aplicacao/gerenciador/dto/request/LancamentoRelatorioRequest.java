package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoRelatorioRequest {

    private String numeroNf;
    private String statusNotaFiscal;

}
