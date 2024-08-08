package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.gerenciador.model.Aplicacao;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AplicacaoRequest {

    private String tipo;
    private String descricao;

    public Aplicacao construirAplicacao() {
        return Aplicacao.builder()
                .tipo(tipo)
                .descricao(descricao)
                .build();
    }
}
