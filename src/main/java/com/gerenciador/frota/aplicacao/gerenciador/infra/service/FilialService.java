package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

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
        List<Filial> filiaisPaginado = this.obterPaginacao(filiais,
                pageable.getPageSize(),
                pageable.getPageNumber());
        Pageable pageableResponse = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        return new PageImpl<>(filiaisPaginado, pageableResponse, filiais.size());
    }

    private List<Filial> obterPaginacao(List<Filial> filiais, int pageSize, int pageNumber) {
        if (pageNumber == 0)
            pageNumber = 1;

        int startIndex = (pageNumber - 1) * pageSize;
        int emdIndex = Math.min(startIndex + pageSize, filiais.size());

        if (emdIndex > filiais.size()) {
            emdIndex = filiais.size();
        }

        if (startIndex < 0 || startIndex >= filiais.size() || emdIndex < 0) {
            return new ArrayList<>();
        }

        return filiais.subList(startIndex, emdIndex);
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
