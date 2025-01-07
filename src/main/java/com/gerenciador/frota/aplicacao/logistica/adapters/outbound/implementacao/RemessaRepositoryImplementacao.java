package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.adapters;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.RemessaRepositoryPorts;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.RemessaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class RemessaRepositoryAdapters implements RemessaRepositoryPorts {

    private final RemessaRepository remessaRepository;

    @Override
    public JpaRemessaEntity salvar(RemessaRequest request) {
        try {
            JpaRemessaEntity jpaRemessaEntitySalvo = remessaRepository.save(request.construirRemessa());
            log.info("[INFO] - Remessa {} foi cadsatrada com sucesso.", jpaRemessaEntitySalvo.getId());
            return jpaRemessaEntitySalvo;
        } catch (Exception e) {
            log.info("[ERRO] - Não foi possivel salvar está remessa.");
            throw new RuntimeException("Não foi possivel cadastrar a remessa.");
        }
    }

    @Override
    public RetornoServicoBase salvar(JpaRemessaEntity jpaRemessaEntity) {
        try {
            JpaRemessaEntity jpaRemessaEntitySalvo = remessaRepository.save(jpaRemessaEntity);
            log.info("[INFO] - Remessa {} foi atualizada com sucesso.", jpaRemessaEntitySalvo.getId());
            return RetornoServicoBase.positivo("Remessa atualziada com sucesso.");
        } catch (Exception e) {
            log.info("[ERRO] - Não foi possivel atualizar está remessa.");
            return RetornoServicoBase.negativo("Remessa não pode ser atualizada.");
        }    }

    @Override
    public List<JpaRemessaEntity> listarTodasRemessas() {
        return remessaRepository.findAll();
    }

    @Override
    public JpaRemessaEntity buscarRemessaPorId(Long codigoRemessa) {
        return remessaRepository.findById(codigoRemessa)
            .orElseThrow(() -> new RuntimeException("Remessa com codigo fornecido não encontrado."));
    }

    @Override
    public RetornoServicoBase deletarRemessaPorId(Long codigoRemessa) {
        try {
            remessaRepository.delete(buscarRemessaPorId(codigoRemessa));
            log.info("[INFO] - Sucesso ao deletar remessa: {}.", codigoRemessa);
            return RetornoServicoBase.positivo("Remessa: " + codigoRemessa + " foi deletada com sucesso.");
        } catch (Exception e) {
            log.info("[ERRO] - Falha ao deletar.  message: {}", e.getMessage());
            return RetornoServicoBase.negativo("Remessa: " + codigoRemessa + " não foi deletada.");
        }
    }

    @Override
    public List<JpaRemessaEntity> listarTodasRemessasPorStatus(StatusRemessa status) {
        return remessaRepository.findAllStatus(status.getDescricao());
    }

}
