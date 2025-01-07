package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums;


import lombok.Getter;

@Getter
public enum StatusColaborador {
    ATIVO("ATIVO"),
    LICENCA("LICENCA"),
    FERIAS("FERIAS"),
    DESATIVADO("DESATIVADO");

    private String descricao;

    StatusColaborador(String descricao) {
        this.descricao = descricao;
    }
}
