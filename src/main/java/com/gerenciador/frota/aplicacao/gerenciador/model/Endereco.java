package com.gerenciador.frota.aplicacao.gerenciador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

    private String logradouro;
    private String cep;

    private String rua;
    private String numero;

    private String estado;

    private String municipio;


}
