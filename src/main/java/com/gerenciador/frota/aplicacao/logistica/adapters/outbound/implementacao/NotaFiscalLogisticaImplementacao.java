package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.infra.ViaCepSerive;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaNotaFiscalLogisticaRepository;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.NotaFiscalLogisticaRepositoryPort;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.MappersDominio;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.MappersRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class NotaFiscalLogisticaImplementacao implements NotaFiscalLogisticaRepositoryPort {

    private final JpaNotaFiscalLogisticaRepository notaFiscalLogisticaRepository;
    private final ViaCepSerive viaCepSerive;

    @Override
    public NotaFiscalLogistica cadastrarNotaFiscal(NotaFiscalLogisticaRequest request) {
        log.info("[START] - Inicio de cadastrar uma nova nota fiscal.");
        try {

            var notaFiscalSalvo = notaFiscalLogisticaRepository
                    .save(MappersRequest
                    .fromNotaFiscaLogisticaToJpaNotaFiscalLogisticaEntity(
                            request,
                            request.getEnderecoRequest()
                                    .getCep() == null
                                    ? null : MappersDominio
                                    .fromEnderecoRequestToEndereco(request
                                            .getEnderecoRequest(),
                                            viaCepSerive)));

            log.info("[END] - Nota fiscal foi salvo.");
            return MappersDominio
                    .fromJpaNotaFiscalLogisticaEntityToNotaFiscalLogistica(notaFiscalSalvo);
        } catch (Exception ex) {
            log.info("[ERRO] - Falhou ao gerar uma nova Nota fiscal.");
            throw new RuntimeException("Falhou ao salvar uma nova nota fiscal.");
        }
    }

    @Override
    public List<NotaFiscalLogistica> buscarTodasNotasFiscais() {
        return null;
    }

    @Override
    public NotaFiscalLogistica buscarNotasFiscaisPorCodigo(Long codigoNotaFiscal) {
        return null;
    }

    @Override
    public RetornoServicoBase atualziarNotaFiscal(Long codigoNotaFiscal, NotaFiscalLogisticaRequest request) {
        return null;
    }

    @Override
    public RetornoServicoBase deletarNotaFiscal(Long codigoNotaFiscal) {
        return null;
    }
}
