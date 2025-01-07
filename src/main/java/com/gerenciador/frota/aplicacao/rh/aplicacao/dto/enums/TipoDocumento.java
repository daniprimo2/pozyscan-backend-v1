package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums;

import lombok.Getter;

@Getter
public enum TipoDocumento {
    RG("RG"),
    CPF("CPF"),
    CNH("CNH");


    private String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }
}
