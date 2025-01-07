package com.gerenciador.frota.aplicacao.logistica.utils.dto.request;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoViagem;
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
