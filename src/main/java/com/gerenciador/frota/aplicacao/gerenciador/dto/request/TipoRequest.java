package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.gerenciador.model.Tipo;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoRequest {

    private String nome;
    private String descricao;

    public Tipo construirTipo() {
        return Tipo.builder()
                .nome(this.nome)
                .descricao(this.descricao)
                .build();
    }
}
