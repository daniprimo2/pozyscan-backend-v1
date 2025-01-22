package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PrecoProdutoRequest {

    private Double valorLiquido;


    private Double valorBruto;

}
