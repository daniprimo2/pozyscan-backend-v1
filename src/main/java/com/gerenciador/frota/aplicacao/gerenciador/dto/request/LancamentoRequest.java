package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoRequest {

    private Long aplicacaoId;
    private Long filialId;
    private String placaVeiculo;
    private Long fornecedorId;
    private String centroCusto;
    private String notaFiscalId;

}
