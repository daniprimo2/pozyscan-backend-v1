package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilialResponse {

    private Long id;
    private String nome;
    private String centroDeCusto;
    private String patente;

}
