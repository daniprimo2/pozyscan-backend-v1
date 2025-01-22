package com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaNotaFiscalLogisticaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.NotaFiscalLogistica;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.NotaFiscalLogisticaRequest;

public interface INotaFiscalCasoDeUso {

    JpaNotaFiscalLogisticaEntity registrarNotaFiscal(NotaFiscalLogisticaRequest request);

    JpaNotaFiscalLogisticaEntity buscarNotaPeloCodigo(Long id);

    RetornoServicoBase atualizarNotaFiscal(Long id, NotaFiscalLogisticaRequest request);

    RetornoServicoBase deletarNotaFiscasl(Long id);


}
