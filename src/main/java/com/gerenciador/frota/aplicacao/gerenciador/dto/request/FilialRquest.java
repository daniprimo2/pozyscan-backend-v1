package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.gerenciador.model.Filial;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilialRquest {

    private String nome;
    private String centroDeCusto;
    private String patente;

    public Filial costruirFilial() {
        return Filial.builder()
                .nome(nome)
                .centroDeCusto(centroDeCusto)
                .patente(patente)
                .build();
    }
}
