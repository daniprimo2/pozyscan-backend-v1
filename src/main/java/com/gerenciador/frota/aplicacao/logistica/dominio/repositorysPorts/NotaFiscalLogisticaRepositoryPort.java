package com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;

import java.util.List;

public interface NotaFiscalLogisticaRepositoryPort {

    NotaFiscalLogistica cadastrarNotaFiscal(NotaFiscalLogisticaRequest request);

    List<NotaFiscalLogistica> buscarTodasNotasFiscais();
    NotaFiscalLogistica buscarNotasFiscaisPorCodigo(Long codigoNotaFiscal);

    RetornoServicoBase atualziarNotaFiscal(Long codigoNotaFiscal, NotaFiscalLogisticaRequest request);
    RetornoServicoBase deletarNotaFiscal(Long codigoNotaFiscal);





}
