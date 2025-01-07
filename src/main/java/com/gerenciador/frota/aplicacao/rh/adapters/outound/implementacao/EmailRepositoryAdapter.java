package com.gerenciador.frota.aplicacao.rh.adapters.outound.implementacao;

import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.TipoContato;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.EmailEmtity;
import com.gerenciador.frota.aplicacao.rh.dominio.repositoysPorts.EmailRepositoryPort;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.persistencia.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class EmailRepositoryAdapter implements EmailRepositoryPort {


    private final EmailRepository emailRepository;

    @Override
    public EmailEmtity buscarEmailPorTipo(Long codigoEmail, TipoContato tipoContato) {
        return emailRepository.findByCodigoEmailAndTipoContato(codigoEmail, tipoContato.getDescricao());
    }

    @Override
    public EmailEmtity salvar(EmailEmtity emailEmtity) {
        return emailRepository.save(emailEmtity);
    }

    @Override
    public EmailEmtity atualizarEmail(Long codigoEmail, EmailEmtity emailEmtity) {
        EmailEmtity emailEmtityAtual = this.buscarEmailPorTipo(codigoEmail, emailEmtity.getTipoContato());
        return emailRepository.save(emailEmtityAtual.atualizarEmail(codigoEmail, emailEmtity));
    }
}
