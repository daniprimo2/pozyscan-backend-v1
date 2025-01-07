package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.RemessaRepositoryPorts;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaRemessaRepository;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RemessaRepositoryImplementacao implements RemessaRepositoryPorts {

    private final JpaRemessaRepository jpaRemessaRepository;

    private Remessa remessa;
    private JpaRemessaEntity jpaRemessaEntity;


    public RemessaRepositoryImplementacao(JpaRemessaRepository jpaRemessaRepository) {
        this.jpaRemessaRepository = jpaRemessaRepository;
    }


    @Override
    public RemessaResponse salvar(RemessaRequest request) {
        try {
            JpaRemessaEntity jpaRemessaEntitySalvo = jpaRemessaRepository.save(request.construirRemessa());
            log.info("[INFO] - Remessa {} foi cadsatrada com sucesso.", jpaRemessaEntitySalvo.getId());
            return Mappers.fromJpaRemessaEntityToRemessaResponse(jpaRemessaEntitySalvo);
        } catch (Exception e) {
            log.info("[ERRO] - Não foi possivel salvar está remessa.");
            throw new RuntimeException("Não foi possivel cadastrar a remessa.");
        }
    }

    @Override
    public RetornoServicoBase salvar(JpaRemessaEntity jpaRemessaEntity) {
        try {
            JpaRemessaEntity jpaRemessaEntitySalvo = jpaRemessaRepository.save(jpaRemessaEntity);
            log.info("[INFO] - Remessa {} foi atualizada com sucesso.", jpaRemessaEntitySalvo.getId());
            return RetornoServicoBase.positivo("Remessa atualizada com sucesso.");
        } catch (Exception e) {
            log.info("[ERRO] - Não foi possivel atualizar está remessa.");
            return RetornoServicoBase.negativo("Remessa não pode ser atualizada.");
        }    }

    @Override
    public List<Remessa> listarTodasRemessas() {
        return jpaRemessaRepository.findAll().stream().map(Mappers::fromJpaRemessaEntityToRemessa).toList();
    }

    @Override
    public Remessa buscarRemessaPorId(Long codigoRemessa) {
        return jpaRemessaRepository.findById(codigoRemessa).map(Mappers::fromJpaRemessaEntityToRemessa)
            .orElseThrow(() -> new RuntimeException("Remessa com codigo fornecido não encontrado."));
    }

    @Override
    public RetornoServicoBase deletarRemessaPorId(Long codigoRemessa) {
        try {
            Remessa remessa = buscarRemessaPorId(codigoRemessa);
            log.info("[INFO] - Remessa encontrada do cliente: {}.", remessa.getCliente());
            JpaRemessaEntity jpaRemessaEntity = Mappers.fromRemessaToJpaRemessaEntity(remessa);
            log.info("[INFO] - Remessa mapeada para a remessa entiti: {}.", remessa.getCliente());
            jpaRemessaRepository.delete(jpaRemessaEntity);
            log.info("[INFO] - Sucesso ao deletar remessa: {}.", codigoRemessa);
            return RetornoServicoBase.positivo("Remessa: " + codigoRemessa + " foi deletada com sucesso.");
        } catch (Exception e) {
            log.info("[ERRO] - Falha ao deletar.  message: {}", e.getMessage());
            return RetornoServicoBase.negativo("Remessa: " + codigoRemessa + " não foi deletada.");
        }
    }

    @Override
    public List<Remessa> listarTodasRemessasPorStatus(StatusRemessa status) {
        return jpaRemessaRepository.findAllStatus(status.getDescricao()).stream().map(Mappers::fromJpaRemessaEntityToRemessa).toList();
    }

}
