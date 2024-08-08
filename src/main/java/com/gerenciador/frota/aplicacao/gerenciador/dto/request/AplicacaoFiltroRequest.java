package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AplicacaoFiltroRequest {

    private String tipo;
    private String descricao;
    private String id;

}
