package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectsResponse {

    private List<SelectPlacaResponse> placas;
    private List<SelectAplicacaoResponse> aplicacoes;
    private List<SelectFilialResponse> filiais;
    private List<SelectFornecedoresResponse> fornecedores;

}
