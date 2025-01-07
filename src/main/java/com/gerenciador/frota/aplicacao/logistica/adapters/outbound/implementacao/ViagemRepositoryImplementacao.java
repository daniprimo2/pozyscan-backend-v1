package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaViagemEntity;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Viagem;
import com.gerenciador.frota.aplicacao.logistica.dominio.repositorysPorts.ViagemRepositoryPort;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.FiltroViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.ViagemRequest;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.persistencia.JpaViagemRepository;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class ViagemRepositoryImplementacao implements ViagemRepositoryPort {


    private final JpaViagemRepository jpaViagemRepository;

    @Override
    public Viagem salvar(ViagemRequest request) {
        JpaViagemEntity vaigemSalva = jpaViagemRepository.save(montarViagem(request));
        return fromJpaViagemEntityToViagem(vaigemSalva);
    }

    @Override
    public Viagem salvar(JpaViagemEntity jpaViagemEntity) {
        return fromJpaViagemEntityToViagem(jpaViagemRepository.save(jpaViagemEntity));
    }

    @Override
    public List<Viagem> listar() {
        return fromListaJpaViagensToListaViagem(jpaViagemRepository.findAll());
    }

    @Override
    public List<Viagem> listar(FiltroViagemRequest filtroRequest) {

        System.out.println("Request aqui é: " + filtroRequest.toString());

        List<JpaViagemEntity> allFiltro = jpaViagemRepository.findAllFiltro(filtroRequest.getDataViagemProgramada(),
                filtroRequest.getTipoViagem());

        System.out.println("Aqui ele trouxe: "+ allFiltro.size());


        return allFiltro.stream().map(Mappers::fromJpaViagemEntityToViagem).toList();
    }

    public List<JpaViagemEntity> listars(FiltroViagemRequest filtroRequest) {
        return jpaViagemRepository.findAllFiltro(filtroRequest.getDataViagemProgramada(),
                filtroRequest.getTipoViagem());
    }
    @Override
    public Viagem buscarViagemPorId(Long codigoViagem) {
        return jpaViagemRepository.findById(codigoViagem)
                .map(Mappers::fromJpaViagemEntityToViagem)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));
    }

    @Override
    public RetornoServicoBase deletarViagemPorId(Long codigoViagem) {
        try {
            jpaViagemRepository.delete(fromViagemToJpaViagemEntity(buscarViagemPorId(codigoViagem)));
            return RetornoServicoBase.positivo("Viagem deletada com sucesso.");
        } catch (Exception e) {
            return RetornoServicoBase.negativo("Não foi possivel deletar a viagem.");
        }
    }

    private JpaViagemEntity montarViagem(ViagemRequest request) {
        return request.construirViagem();
    }
}
