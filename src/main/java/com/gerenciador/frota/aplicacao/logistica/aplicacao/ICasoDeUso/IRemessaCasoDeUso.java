package com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.infraestrutura.dto.request.RemessaRequest;

import java.util.List;

public interface ICadastrarViagem {

    Remessa cadastrarNovaRemessa(RemessaRequest request);

    List<Remessa> listarTodasRemessas();

    List<Remessa> listarTodasRemessasPorTipo(StatusRemessa status);

    Remessa buscarRemessaPorId(Long id);

    RetornoServicoBase atualziarRemessaPorId(Long id, RemessaRequest request);


    RetornoServicoBase deletarRemessa(Long id);

}
