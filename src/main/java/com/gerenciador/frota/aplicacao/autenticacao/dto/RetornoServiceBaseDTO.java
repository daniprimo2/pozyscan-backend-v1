package com.gerenciador.frota.aplicacao.autenticacao.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetornoServiceBaseDTO {

    private Integer codigo;
    private String descricaoErro;
    private boolean funcionou;

    public static RetornoServiceBaseDTO retornoPositivo(String descricaoErro) {
        return RetornoServiceBaseDTO.builder()
                .codigo(1)
                .descricaoErro(descricaoErro)
                .funcionou(true)
                .build();
    }

    public static RetornoServiceBaseDTO retornoNegativos(String descricaoErro) {
        return RetornoServiceBaseDTO.builder()
                .codigo(-1)
                .descricaoErro(descricaoErro)
                .funcionou(false)
                .build();
    }
}
