package com.gerenciador.frota.aplicacao.autenticacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetornoServicoBase {

    private String descricao;
    private Boolean funcionou;
    private int codErro;

    public static RetornoServicoBase positivo(String descricao) {
        return RetornoServicoBase.builder()
                .codErro(1)
                .funcionou(true)
                .descricao(descricao)
                .build();
    }

    public static RetornoServicoBase negativo(String descricao) {
        return RetornoServicoBase.builder()
                .codErro(-1)
                .funcionou(false)
                .descricao(descricao)
                .build();
    }

}
