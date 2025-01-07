package com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;

import java.util.List;

public interface RemessaRepositoryPorts {

    RemessaResponse salvar(RemessaRequest request);
    RetornoServicoBase salvar(JpaRemessaEntity jpaRemessaEntity);

    List<Remessa> listarTodasRemessas();

    Remessa buscarRemessaPorId(Long codigoRemessa);

    RetornoServicoBase deletarRemessaPorId(Long codigoRemessa);


    List<Remessa> listarTodasRemessasPorStatus(StatusRemessa status);
}
