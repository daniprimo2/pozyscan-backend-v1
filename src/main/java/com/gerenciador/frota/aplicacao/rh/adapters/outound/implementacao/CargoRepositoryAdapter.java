package com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.CargoRequest;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.FiltroCargoRequest;
import com.gerenciador.frota.aplicacao.rh.dominio.exceptions.CargoNaoEncontradoException;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.CargoEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.CargoRepositoryPort;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.CargoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class CargoRepositoryAdapter implements CargoRepositoryPort {


    private  final CargoRepository cargoRepository;



    @Override
    public CargoEntity salva(CargoEntity cargoEntity) {
        CargoEntity cargoEntitySalvo = cargoRepository.save(cargoEntity);
        log.info("Cadastrando novo cargo: {}", cargoEntitySalvo.getNomeCargo());
        return cargoEntitySalvo;
    }

    @Override
    public List<CargoEntity> listarTodos() {
        List<CargoEntity> listaDeCargoEntities = cargoRepository.findAll();
        log.info("Foi encontrado {} cargos", listaDeCargoEntities.size());
        return listaDeCargoEntities;
    }

    @Override
    public List<CargoEntity> listarPorFiltro(FiltroCargoRequest filtroCargoRequest) {
        List<CargoEntity> cargoPorCargoPorDescCargoEntities = cargoRepository.findCargoPorCargoPorDescCargo(filtroCargoRequest.getCargo(),
                filtroCargoRequest.getDescricaoCargo());
        log.info("Lista de carfo por filtro: {}", cargoPorCargoPorDescCargoEntities);
        return cargoPorCargoPorDescCargoEntities;
    }

    @Override
    public RetornoServicoBase atualizar(Long codigoCargo, CargoRequest cargoRequest) {
        CargoEntity cargoEntity = this.buscarPorId(codigoCargo);
        try {
            log.info("Atualizar o cargo {}", codigoCargo);
            cargoRepository.save(cargoRequest.atualizandoCargo(cargoEntity.getId()));
        } catch (Exception e) {
            log.info("Cargo {} não foi atualizado.", codigoCargo);
            return RetornoServicoBase.negativo("Não foi possivel alterar este cadastro de cargo");
        }
        log.info("Cargo {} foi atualizado.", codigoCargo);
        return RetornoServicoBase.positivo("Cargo "+ cargoEntity.getId() +" foi alterado com sucesso.");

    }

    @Override
    public RetornoServicoBase desativar(Long codigoCargo) {
        CargoEntity cargoEntity;
        try {
            cargoEntity = this.buscarPorId(codigoCargo);
            log.info("Desativar o cargo {}.", codigoCargo);
            cargoRepository.delete(cargoEntity);
        } catch (CargoNaoEncontradoException e) {
            log.info("Cargo {} nao foi encontrado.", codigoCargo);
            throw new CargoNaoEncontradoException(e.getMessage());
        }

        log.info("Cargo {} foi desativado.", codigoCargo);
        return RetornoServicoBase.positivo("Cargo"+ cargoEntity.getId() +" foi deletado com sucesso.");
    }

    @Override
    public CargoEntity buscarPorId(Long codigoDoCargo) throws CargoNaoEncontradoException {
        return cargoRepository.findById(codigoDoCargo)
                .orElseThrow(() -> new CargoNaoEncontradoException("Cargo não foi encontrado."));
    }
}
