package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.Util.Utils.UtilPaginacao;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.CargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.FiltroCargoRequest;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.CargoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GerenciarCargoCasoDeUso {

    private final CargoRepositoryPort cargoRepositoryPort;

    public CargoEntity criarCargo(CargoRequest request) {
        CargoEntity novoCargoEntity = new CargoEntity(request.getNomeCargo(),
                request.getDescricaoCargo(),
                request.getTipoCargo());
        return cargoRepositoryPort.salva(novoCargoEntity);
    }

    public List<CargoEntity> listarCargos() {
        return cargoRepositoryPort.listarTodos();
    }

    public PageImpl<?> listarCargosPorFiltro(FiltroCargoRequest filtroCargoRequest, Pageable pageable){
        return UtilPaginacao.obterPaginacao(cargoRepositoryPort.listarPorFiltro(filtroCargoRequest), pageable);
    }

    public RetornoServicoBase atualizar(Long codigoCargo, CargoRequest cargoRequest) {
        return cargoRepositoryPort.atualizar(codigoCargo, cargoRequest);
    }

    public RetornoServicoBase desativar(Long codigoCargo) {
        return cargoRepositoryPort.desativar(codigoCargo);
    }

    public CargoEntity buscarPorId(Long codigoDoCargo) {
        return cargoRepositoryPort.buscarPorId(codigoDoCargo);
    }

}
