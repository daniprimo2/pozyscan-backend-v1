package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TelefoneRequest {

    private String telefone;
    private String tipo;

}
