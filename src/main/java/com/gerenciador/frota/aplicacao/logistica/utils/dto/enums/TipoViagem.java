package com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums;

import lombok.Getter;

@Getter
public enum TipoViagem {
    FROTA_PROPRIA("FROTA_PROPRIA"),
    FROTA_AGREGADA("FROTA_AGREGADA"),
    NAO_REMUNERADA("NAO_REMUNERADA"),
    MANUTENCAO("MANUTENCAO");

    private String descricao;

    TipoViagem(String descricao) {
        this.descricao = descricao;
    }
}
