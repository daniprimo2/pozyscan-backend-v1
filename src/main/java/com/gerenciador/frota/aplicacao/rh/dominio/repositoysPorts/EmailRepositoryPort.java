package com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;

public interface EmailRepositoryPort {

    EmailEmtity buscarEmailPorTipo(Long codigoEmail, TipoContato tipoContato);
    EmailEmtity salvar(EmailEmtity emailEmtity);

    EmailEmtity atualizarEmail(Long codigoEmail, EmailEmtity emailEmtity);

}
