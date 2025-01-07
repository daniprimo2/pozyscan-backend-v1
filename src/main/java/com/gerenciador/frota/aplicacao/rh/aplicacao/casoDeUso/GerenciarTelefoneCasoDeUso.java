package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.TelefonesEntity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.TelefoneRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GerenciarTelefoneCasoDeUso {

    private final TelefoneRepositoryPort telefoneRepositoryPort;

    public TelefonesEntity salvarTelefones(TelefonesEntity telefonesEntity){
        return telefoneRepositoryPort.salvar(telefonesEntity);
    }

    public TelefonesEntity atualizarTelefones(Long codigoTelefone, TelefonesEntity telefonesEntity) {
        return telefoneRepositoryPort.atualizarTelefones(codigoTelefone, telefonesEntity);
    }

}
