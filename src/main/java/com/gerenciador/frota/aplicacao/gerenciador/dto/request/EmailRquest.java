package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailRquest {

    private String email;
    private String tipo;

}
