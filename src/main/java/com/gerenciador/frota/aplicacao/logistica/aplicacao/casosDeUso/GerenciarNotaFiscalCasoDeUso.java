package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.NotaFiscalLogisticaImplementacao;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso.INotaFiscalCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.NotaFiscalLogisticaRepositoryPort;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.MappersJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GerenciarNotaFiscalCasoDeUso implements INotaFiscalCasoDeUso {

    private final NotaFiscalLogisticaRepositoryPort fiscalLogisticaRepositoryPort;

    private final MappersJpaEntity mappersJpaEntity;

    @Override
    public JpaNotaFiscalLogisticaEntity registrarNotaFiscal(NotaFiscalLogisticaRequest request) {
        return MappersJpaEntity
                .fromNotaFiscalToJpaNotaFiscalEntity(fiscalLogisticaRepositoryPort
                        .cadastrarNotaFiscal(request));
    }

    @Override
    public JpaNotaFiscalLogisticaEntity buscarNotaPeloCodigo(Long id) {
        return new JpaNotaFiscalLogisticaEntity();
    }

    @Override
    public RetornoServicoBase atualizarNotaFiscal(Long id, NotaFiscalLogisticaRequest request) {
        return RetornoServicoBase.positivo("");
    }

    @Override
    public RetornoServicoBase deletarNotaFiscasl(Long id) {
        return RetornoServicoBase.positivo("");
    }
}
