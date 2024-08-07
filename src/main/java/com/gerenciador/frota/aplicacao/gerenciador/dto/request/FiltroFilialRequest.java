package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FiltroFilialRequest {

    private String nome;
    private String centroDeCusto;
    private String patente;
    private String id;

}
