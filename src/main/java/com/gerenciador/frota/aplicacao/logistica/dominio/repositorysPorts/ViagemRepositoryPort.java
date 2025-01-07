package com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;

import java.util.List;

public interface ViagemRepositoryPort {

    Viagem salvar(ViagemRequest request);
    Viagem salvar(JpaViagemEntity jpaViagemEntity);

    List<Viagem> listar();

    List<Viagem> listar(FiltroViagemRequest filtroRequest);

    Viagem buscarViagemPorId(Long codigoViagem);

    RetornoServicoBase deletarViagemPorId(Long codigoViagem);

}
