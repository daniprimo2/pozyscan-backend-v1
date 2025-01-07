package com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;

import java.util.List;

public interface IRemessaCasoDeUso {

    RemessaResponse cadastrarNovaRemessa(RemessaRequest request);

    List<Remessa> listarTodasRemessas();

    List<Remessa> listarTodasRemessasPorTipo(StatusRemessa status);

    Remessa buscarRemessaPorId(Long id);

    RetornoServicoBase atualziarRemessaPorId(Long id, RemessaRequest request);


    RetornoServicoBase deletarRemessa(Long id);

}
