package com.gerenciador.frota.aplicacao.autenticacao.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TipoResponse {

    private String nome;
    private String descricao;

}
