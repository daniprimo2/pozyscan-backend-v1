package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DimensaoProdutoRequest {

    private Double largura;

    private Double altura;

    private Double comprimento;
}
