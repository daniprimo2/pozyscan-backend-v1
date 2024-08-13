package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltroVeiculoRequest {

    private String placa;
    private String modelo;
    private String tipo;
    private String categoria;

}
