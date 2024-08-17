package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParcelaRelatorioRequest {

    private String centroDeCusto;
    private String numeroNotaFiscal;
    private String dataVencimento;
    private String statusPagamentos;

}
