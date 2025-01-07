package com.gerenciador.frota.aplicacao.logistica.utils.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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

    // Método JsonCreator para mapear o valor da string para o Enum
    @JsonCreator
    public static TipoViagem fromString(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Valor vazio ou nulo fornecido para TipoViagem.");
        }
        for (TipoViagem tipo : TipoViagem.values()) {
            if (tipo.descricao.equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para TipoViagem: " + descricao);
    }

    // Método JsonValue para mapear o valor de String de volta para o Enum
    @JsonValue
    public String getDescricao() {
        return descricao;
    }


}
