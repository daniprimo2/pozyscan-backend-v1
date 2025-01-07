package com.gerenciador.frota.aplicacao.gerenciador.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DashboardResponse {

    private String totalDeDispesas;
    private String totalDeDispesasPagas;
    private String totalDeDispesasAPagar;
    private Integer totalVeiculos;
    private Integer frotaPropria;
    private Integer frotaAgregada;
    private List<AplicacaoResponse> aplicacoes;
    private List<VeiculoResponse> veiculos;

}
