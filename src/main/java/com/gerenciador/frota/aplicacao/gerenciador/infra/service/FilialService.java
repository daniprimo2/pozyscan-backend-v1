package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.Util.Utils.UtilPaginacao;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FilialRquest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FiltroFilialRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.FilialResponse;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectFilialResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.FilialRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Filial;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FilialService {

    @Autowired
    private FilialRepository filialRepository;

    public FilialResponse adicionarNovaFilial(FilialRquest filialRquest) {
        log.info("[START] - Adicionando uma nova filial.");
        Filial filial = filialRquest.costruirFilial();
        Filial filialSalvo = filialRepository.save(filial);
        log.info("[END] - Filial adicionado com sucesso.");
        return filialSalvo.construirResponse();
    }

    public PageImpl<?> buscarFiliaisComFiltro(FiltroFilialRequest filtroFilialRequest,
                                              Pageable pageable) {
        List<Filial> filiais = this.getFiliais(filtroFilialRequest);
        return UtilPaginacao.obterPaginacao(filiais, pageable);
    }

    private List<Filial> getFiliais(FiltroFilialRequest filtroFilialRequest) {
        List<Filial> listaDeFiliais = new ArrayList<>();
        if (!filtroFilialRequest.getId().isEmpty()) {
            log.info("[INFO] - Filtro pelo id do funcionario.");
            listaDeFiliais.add(filialRepository.findById(Long.valueOf(filtroFilialRequest.getId()))
                    .orElseThrow(() -> new RuntimeException("Filial não encontrado.")));
        } else {
            log.info("[INFO] - Filtro pelas demais informações.");
            listaDeFiliais.addAll(filialRepository.findAllByNomeAndCentroDeCustoAndPatente(
                    filtroFilialRequest.getNome(),
                    filtroFilialRequest.getCentroDeCusto(),
                    filtroFilialRequest.getPatente()));
        }

        return listaDeFiliais;
    }

    public RetornoServicoBase deletarFilialPorId(Long id) {
        log.info("[START] - Deletar por filial.");
        var filial = filialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial não encontrado."));
        filialRepository.delete(filial);
        log.info("[END] - Filial foi deletada.");
        return RetornoServicoBase.positivo(filial.getNome() + " excluido com sucesso.");
    }

    public Filial buscarFilialPorId(Long filialId) {
        return filialRepository.findById(filialId)
                .orElseThrow(() -> new RuntimeException("Filial não foi encntrada."));
    }

    public List<Filial> buscarTodasFiliais() {
        return filialRepository.findAll();
    }
}
