package com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts;

import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.ViagemRequest;

public interface ViagemRepositoryPort {

    Viagem salvar(ViagemRequest request);

}
