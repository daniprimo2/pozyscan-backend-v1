package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConcluirPagamentoDTO {

    private Long idParcela;
    private String numeroNotaFiscal;

}
