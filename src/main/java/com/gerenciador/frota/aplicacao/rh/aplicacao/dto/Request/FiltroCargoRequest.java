package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroCargoRequest {

    private String descricaoCargo;
    private String cargo;

}
