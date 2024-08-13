package com.gerenciador.frota.aplicacao.gerenciador.dto;

import lombok.Getter;

@Getter
public enum FormaPagamento {
    A_VISTA("Pagamento a vista"),
    A_VISTA_A_PRAZO("Pagamento a vista com um prazo de vencimento."),
    PARCELADO("Pagamento parcelado");

    private String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }
}
