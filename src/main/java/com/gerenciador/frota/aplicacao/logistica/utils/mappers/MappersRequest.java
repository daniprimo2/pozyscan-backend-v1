package com.gerenciador.frota.aplicacao.logistica.utils.mappers;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import org.springframework.stereotype.Component;

@Component
public class MappersRequest {

    public static JpaNotaFiscalLogisticaEntity fromNotaFiscaLogisticaToJpaNotaFiscalLogisticaEntity(NotaFiscalLogisticaRequest request, Endereco endereco){
        return JpaNotaFiscalLogisticaEntity.builder()
                .numeroNotaFisal(request.getNumeroNotaFisal())
                .dataEmissao(request.getDataEmissao())
                .valorTotal(request.getValorTotal())
                .endereco(endereco)
                .build();
    }
}
