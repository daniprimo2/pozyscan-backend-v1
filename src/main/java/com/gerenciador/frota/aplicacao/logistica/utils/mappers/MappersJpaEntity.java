package com.gerenciador.frota.aplicacao.logistica.utils.mappers;

import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers.fromViagemToJpaViagemEntity;

@Component
public class MappersJpaEntity {

    public static JpaNotaFiscalLogisticaEntity fromNotaFiscalToJpaNotaFiscalEntity(NotaFiscalLogistica notaFiscalLogistica){
        return JpaNotaFiscalLogisticaEntity.builder()
                .endereco(notaFiscalLogistica.getEndereco())
                .valorTotal(notaFiscalLogistica.getValorTotal())
                .dataEmissao(notaFiscalLogistica.getDataEmissao())
                .codigoNotaFiscal(notaFiscalLogistica.getCodigoNotaFiscal())
                .numeroNotaFisal(notaFiscalLogistica.getNumeroNotaFisal())
                .jpaRemessaEntity(notaFiscalLogistica.getRemessa() == null ? null : MappersJpaEntity
                        .fromRemessaToJpaRemessaEntity(notaFiscalLogistica.getRemessa()))
                .build();
    }

    private static JpaRemessaEntity fromRemessaToJpaRemessaEntity(Remessa remessa) {

        List<JpaNotaFiscalLogisticaEntity> listasNfs = new ArrayList<>();
        for (NotaFiscalLogistica nf: remessa.getNotaFiscalLogisticas()) {
            listasNfs.add(fromNotaFiscalToJpaNotaFiscalEntity(nf));
        }


        return JpaRemessaEntity.builder()
                .pesoTotal(remessa.getPesoTotal())
                .id(remessa.getId())
                .volumeTotal(remessa.getVolumeTotal())
                .dataCriacao(remessa.getDataCriacao())
                .cliente(remessa.getCliente())
                .statusRemessa(remessa.getStatusRemessa())
                .jpaViagemEntity(fromViagemToJpaViagemEntity(remessa.getViagem()))
                .jpaNotaFiscalLogisticaEntities(listasNfs)
                .build();
    }

    public static JpaNotaFiscalLogisticaEntity fromNotaFiscalRequestToJpaNotaFiscalLogisticaEntity(NotaFiscalLogistica notaFiscalLogisticaRecuperado) {
        return JpaNotaFiscalLogisticaEntity.builder()
                .jpaRemessaEntity(notaFiscalLogisticaRecuperado.getRemessa() == null ? null : MappersJpaEntity.fromRemessaToJpaRemessaEntity(notaFiscalLogisticaRecuperado.getRemessa()))
                .numeroNotaFisal(notaFiscalLogisticaRecuperado.getNumeroNotaFisal())
                .codigoNotaFiscal(notaFiscalLogisticaRecuperado.getCodigoNotaFiscal())
                .endereco(notaFiscalLogisticaRecuperado.getEndereco())
                .dataEmissao(notaFiscalLogisticaRecuperado.getDataEmissao())
                .valorTotal(notaFiscalLogisticaRecuperado.getValorTotal())
                .build();
    }

    public static JpaNotaFiscalLogisticaEntity fromNotaFiscalRequestToJpaNotaFiscalLogisticaEntity(NotaFiscalLogisticaRequest request, NotaFiscalLogistica notaFiscalLogistica) {
        return JpaNotaFiscalLogisticaEntity.builder()
                .jpaRemessaEntity(notaFiscalLogistica.getRemessa() == null ? null : MappersJpaEntity.fromRemessaToJpaRemessaEntity(notaFiscalLogistica.getRemessa()))
                .numeroNotaFisal(request.getNumeroNotaFisal())
                .endereco(request.getEnderecoRequest() == null ? null : MappersDominio.fromEnderecoRequestToEndereco(request.getEnderecoRequest()))
                .dataEmissao(request.getDataEmissao())
                .valorTotal(request.getValorTotal())
                .codigoNotaFiscal(notaFiscalLogistica.getCodigoNotaFiscal())
                .build();
    }
}
