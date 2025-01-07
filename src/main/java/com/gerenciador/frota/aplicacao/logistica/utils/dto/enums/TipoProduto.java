package com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums;

import lombok.Getter;

@Getter
public enum TipoProduto {
    UNIDADE("UNIDADE"),
    PACOTE_FECHADO("PACOTE_FECHADO");

    private String descricao;

    TipoProduto(String descricao) {
        this.descricao = descricao;
    }
}
