package com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotaFiscalLogisticaRequest {

    private String numeroNotaFisal;


    public NotaFiscalLogistica construirNotaFiscal() {
        return NotaFiscalLogistica.builder()
                .numeroNotaFisal(this.numeroNotaFisal)
                .build();
    }
}
