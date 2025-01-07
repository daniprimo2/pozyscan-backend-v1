package com.gerenciador.frota.aplicacao.logistica.utils.dto.enums;

import lombok.Getter;

@Getter
public enum StatusRemessa {
    VAZIA("VAZIA"),
    PROGRAMADA("PROGRAMADA"),
    CONCLUIDA("CONCLUIDA"),
    CANCELADA("CANCELADA");


    private String descricao;

    StatusRemessa(String descricao) {
        this.descricao = descricao;
    }
}
