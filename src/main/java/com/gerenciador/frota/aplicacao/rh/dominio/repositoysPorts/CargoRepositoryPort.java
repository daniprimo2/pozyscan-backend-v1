package com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.CargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.FiltroCargoRequest;
import com.gerenciador.frota.aplicacao.rh.dominio.exceptions.CargoNaoEncontradoException;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.model.Cargo;

import java.util.List;

public interface CargoRepositoryPort {

    CargoEntity salva(CargoEntity cargoEntity);

    List<CargoEntity> listarTodos();

    List<CargoEntity> listarPorFiltro(FiltroCargoRequest filtroCargoRequest);

    RetornoServicoBase atualizar(Long codigoCargo, CargoRequest cargoRequest);
    RetornoServicoBase desativar(Long codigoCargo);

    CargoEntity buscarPorId(Long codigoDoCargo) throws CargoNaoEncontradoException;

}
