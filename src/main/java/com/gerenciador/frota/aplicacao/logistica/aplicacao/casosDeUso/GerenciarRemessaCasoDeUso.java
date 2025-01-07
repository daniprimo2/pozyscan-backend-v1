package com.gerenciador.frota.aplicacao.logistica.aplicacao.casosDeUso;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.logistica.aplicacao.ICasoDeUso.IRemessaCasoDeUso;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities.JpaRemessaEntity;
import com.gerenciador.frota.aplicacao.logistica.adapters.outbound.implementacao.RemessaRepositoryImplementacao;
import com.gerenciador.frota.aplicacao.logistica.dominio.model.Remessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.StatusRemessa;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.request.RemessaRequest;
import com.gerenciador.frota.aplicacao.logistica.utils.dto.response.RemessaResponse;
import com.gerenciador.frota.aplicacao.logistica.utils.mappers.Mappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class GerenciarRemessaCasoDeUso implements IRemessaCasoDeUso {

    private final RemessaRepositoryImplementacao remessaRepositoryImplementacao;

    @Override
    public RemessaResponse cadastrarNovaRemessa(RemessaRequest request) {
        log.info("[INFO] - Inicio do processo cadastrar nova remessa.");
        return remessaRepositoryImplementacao.salvar(request);
    }

    @Override
    public List<Remessa> listarTodasRemessas() {
        log.info("[INFO] - Buscando todas as remessas.");
        return remessaRepositoryImplementacao.listarTodasRemessas();
    }

    @Override
    public List<Remessa> listarTodasRemessasPorTipo(StatusRemessa status) {
        log.info("[INFO] - Inicio do processo buscar todas remessa de status: {}", status.getDescricao());
        return remessaRepositoryImplementacao.listarTodasRemessasPorStatus(status);
    }

    @Override
    public Remessa buscarRemessaPorId(Long id) {
        log.info("[INFO] - Inicio buscar remessa de codigo: {}", id);
        return remessaRepositoryImplementacao.buscarRemessaPorId(id);
    }

    @Override
    public RetornoServicoBase atualziarRemessaPorId(Long codigoRemessa, RemessaRequest request) {
        log.info("{INFO} - Inicio do processo de atualizar a remessa de codigo: {} ", codigoRemessa);
        Remessa remessa = remessaRepositoryImplementacao.buscarRemessaPorId(codigoRemessa);
        return remessaRepositoryImplementacao.salvar(atualizarRemessa(remessa, request));
    }

    private JpaRemessaEntity atualizarRemessa(Remessa remessa, RemessaRequest request) {
        remessa.setCliente(request.getCliente());
        return Mappers.fromRemessaToJpaRemessaEntity(remessa);
    }

    @Override
    public RetornoServicoBase deletarRemessa(Long codigoRemessa) {
        log.info("{INFO} - Inicio do processo de deletar a remessa de codigo: {} ", codigoRemessa);
        return remessaRepositoryImplementacao.deletarRemessaPorId(codigoRemessa);
    }
}
