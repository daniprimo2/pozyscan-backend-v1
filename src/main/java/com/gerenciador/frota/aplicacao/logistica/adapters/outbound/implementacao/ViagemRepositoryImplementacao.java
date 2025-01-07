package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.adapters;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.ViagemRepositoryPort;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.ViagemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class ViagemRepositoryAdapters implements ViagemRepositoryPort {


    private final ViagemRepository viagemRepository;

    @Override
    public JpaViagemEntity salvar(ViagemRequest request) {
        return viagemRepository.save(montarViagem(request));
    }

    @Override
    public JpaViagemEntity salvar(JpaViagemEntity jpaViagemEntity) {
        return viagemRepository.save(jpaViagemEntity);
    }

    @Override
    public List<JpaViagemEntity> listar() {
        return viagemRepository.findAll();
    }

    @Override
    public List<JpaViagemEntity> listar(FiltroViagemRequest filtroRequest) {
        return viagemRepository.findAllFiltro(filtroRequest.getDataViagemProgramada(),
                filtroRequest.getTipoViagem());
    }

    @Override
    public JpaViagemEntity buscarViagemPorId(Long codigoViagem) {
        return viagemRepository.findById(codigoViagem)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
    }

    @Override
    public RetornoServicoBase deletarViagemPorId(Long codigoViagem) {
        try {
            viagemRepository.delete(buscarViagemPorId(codigoViagem));
            return RetornoServicoBase.positivo("Viagem deletada com sucesso.");
        } catch (Exception e) {
            return RetornoServicoBase.negativo("Não foi possivel deletar a viagem.");
        }
    }

    private JpaViagemEntity montarViagem(ViagemRequest request) {
        return request.construirViagem();
    }
}
