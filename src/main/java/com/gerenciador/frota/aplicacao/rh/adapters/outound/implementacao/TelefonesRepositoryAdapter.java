package com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.TelefoneRepositoryPort;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.TelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class TelefonesRepositoryAdapter implements TelefoneRepositoryPort {

    private final TelRepository telRepository;

    @Override
    public TelefonesEntity buscarTelefonesPorTipo(Long codigoTelefone, TipoContato tipoContato) {
        return telRepository.findByCodigoTelefoneAndTipoContato(codigoTelefone, tipoContato.getDescricao());
    }

    @Override
    public TelefonesEntity salvar(TelefonesEntity telefonesEntity) {
        return telRepository.save(telefonesEntity);
    }

    @Override
    public TelefonesEntity atualizarTelefones(Long codigoTelefone, TelefonesEntity telefonesEntity) {
        TelefonesEntity telefonePadrao = this.buscarTelefonesPorTipo(codigoTelefone, telefonesEntity.getTipoContato());
        return telRepository.save(telefonePadrao.atualizarTelefone(codigoTelefone, telefonesEntity));
    }
}
