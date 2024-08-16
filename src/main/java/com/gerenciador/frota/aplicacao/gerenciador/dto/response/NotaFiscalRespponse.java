package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalRespponse {

    private String numeroNf;
    private String dataEmissao;
    private String descricaoParcela;
    private String dataDePagamento;
    private String statusPagamento;
    private String valorDoPagamento;

}
