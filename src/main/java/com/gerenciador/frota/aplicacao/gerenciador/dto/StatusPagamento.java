package com.gerenciador.frota.aplicacao.gerenciador.dto;

import lombok.Getter;

@Getter
public enum StatusPagamento {

    PAGO("PAGO"),
    AVISTA_A_PRAZO("AVISTA-COM-PRAZO"),
    EM_ABERTO("PARCELAS-EM-ABERTO");

    private String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }
}
