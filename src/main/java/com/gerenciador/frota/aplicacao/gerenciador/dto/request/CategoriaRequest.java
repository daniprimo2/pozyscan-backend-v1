package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.gerenciador.model.Categoria;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequest {

    private String nome;
    private String descricao;

    public Categoria constuirCategoria() {
        return Categoria.builder()
                .nome(nome)
                .descricao(descricao)
                .build();
    }
}
