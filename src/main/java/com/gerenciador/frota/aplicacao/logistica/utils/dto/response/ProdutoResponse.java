package com.gerenciador.frota.aplicacao.logistica.utils.dto.response;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoResponse {

    private String nomeProduto;

    private String descricaoProduto;

    private TipoProduto tipoProduto;

    private Double pesoLiquido;

    private Double pesoBruto;

    private int quantidade;

    private Double valorLiquido;

    private Double valorBruto;

    private Double largura;

    private Double altura;

    private Double comprimento;

}
