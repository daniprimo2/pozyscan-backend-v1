package com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso;

import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.ViagemRequest;

public interface IViagemCasoDeUso {

    Viagem cadastrarNovaViagem(ViagemRequest request);

}
