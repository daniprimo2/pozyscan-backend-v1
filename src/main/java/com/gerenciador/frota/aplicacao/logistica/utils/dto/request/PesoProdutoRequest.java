package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PesoProdutoRequest {
    private Double pesoLiquido;

    private Double pesoBruto;

}
