package com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;

public interface TelefoneRepositoryPort {

    TelefonesEntity buscarTelefonesPorTipo(Long codigoTelefone, TipoContato tipoContato);
    TelefonesEntity salvar(TelefonesEntity telefonesEntity);

    TelefonesEntity atualizarTelefones(Long codigoTelefone, TelefonesEntity telefonesEntity);

}
