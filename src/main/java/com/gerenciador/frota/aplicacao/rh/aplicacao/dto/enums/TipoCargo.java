package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums;

import lombok.Getter;

@Getter
public enum TipoCargo {

    GERENTE("GERENTE"),
    SUPERVISOR("SUPERVISOR"),
    ANALISTA("ANALISTA"),
    ASSISTENTE("ASSISTENTE"),
    AUXILIAR("AUXILIAR"),
    ESTAGIARIO("ESTAGIARIO"),
    OPERACAO("OPERACAO"),
    TERCEIRO("TERCEIRO"),
    ADMINISTRATIVO("ADMINISTRATIVO"),
    DIRETOR("DIRETOR");

    private String descricao;

    TipoCargo(String descricao) {
        this.descricao = descricao;
    }
}
