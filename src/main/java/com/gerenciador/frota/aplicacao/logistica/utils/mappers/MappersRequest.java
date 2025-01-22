package com.gerenciador.frota.aplicacao.logistica.utils.mappers;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MappersRequest {

    public static JpaNotaFiscalLogisticaEntity fromNotaFiscaLogisticaToJpaNotaFiscalLogisticaEntity(NotaFiscalLogisticaRequest request, Endereco endereco){
        log.info("[START] - Realizando o Mapeamento de logistica para o entity.");
        JpaNotaFiscalLogisticaEntity entity = JpaNotaFiscalLogisticaEntity.builder()
                .numeroNotaFisal(request.getNumeroNotaFisal())
                .dataEmissao(request.getDataEmissao())
                .valorTotal(request.getValorTotal())
                .endereco(endereco == null ? null : endereco)
                .build();
        log.info("[END] - Convertido para o entity.");
        return entity;
    }
}
