package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums;

import lombok.Getter;

@Getter
public enum TipoContato {

    COMERCIAL("COMERCIAL"),
    PESSOAL("PESSOAL"),
    TERCEIRO("TERCEIRO");

    private String descricao;

    TipoContato(String descricao) {
        this.descricao = descricao;
    }
}
  