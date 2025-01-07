package com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.EmailRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class GerenciarEmailCasoDeUso {

    private final EmailRepositoryPort emailRepositoryPort;

    public EmailEmtity buscarEmailPorTipo(Long codigoEmail, TipoContato tipoContato) {
        return emailRepositoryPort.buscarEmailPorTipo(codigoEmail, tipoContato);
    }

    public EmailEmtity salvarEmail(EmailEmtity emailEmtity) {
        return emailRepositoryPort.salvar(emailEmtity);
    }

    public EmailEmtity atualizarEmail(Long codigoEmail, EmailEmtity emailEmtity) {
        return emailRepositoryPort.atualizarEmail(codigoEmail, emailEmtity);
    }


}
