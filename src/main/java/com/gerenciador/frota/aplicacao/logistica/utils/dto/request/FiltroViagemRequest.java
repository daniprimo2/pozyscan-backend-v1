package com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request;

import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums.TipoViagem;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FiltroViagemRequest {

    private String dataViagemProgramada;
    private TipoViagem tipoViagem;

}
