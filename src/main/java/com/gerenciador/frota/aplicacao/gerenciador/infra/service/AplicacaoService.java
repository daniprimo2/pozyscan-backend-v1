package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.Util.Utils.UtilPaginacao;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.AplicacaoFiltroRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.AplicacaoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectAplicacaoResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.AplicacaoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Aplicacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AplicacaoService {

    @Autowired
    private AplicacaoRepository aplicacaoRepository;

    public Aplicacao salvarNovaAplicacao(AplicacaoRequest aplicacaoRequest) {
        Aplicacao aplicacao = aplicacaoRequest.construirAplicacao();
        return aplicacaoRepository.save(aplicacao);
    }

    public PageImpl<?> buscarAplicacoesComFiltro(AplicacaoFiltroRequest aplicacaoFiltroRequest, Pageable pageable) {

        List<Aplicacao> aplicacoes = this.getAplicacoes(aplicacaoFiltroRequest);
        return UtilPaginacao.obterPaginacao(aplicacoes, pageable);
    }

    private List<Aplicacao> getAplicacoes(AplicacaoFiltroRequest aplicacaoFiltroRequest) {
        List<Aplicacao> listaAplicacoes = new ArrayList<>();
        if (!aplicacaoFiltroRequest.getId().isEmpty()){
            log.info("[INFO] - Filtro pelo id das aplicacoes.");
            listaAplicacoes.add(aplicacaoRepository.findById(Long
                            .valueOf(aplicacaoFiltroRequest.getId()))
                    .orElseThrow(() -> new RuntimeException("Aplicação nao foi encontrada.")));
        } else {
            log.info("[INFO] - Filtro pelas demais informações.");
            listaAplicacoes.addAll(aplicacaoRepository.findAllByTipoAndDescricaoAndId(
                    aplicacaoFiltroRequest.getTipo(),
                    aplicacaoFiltroRequest.getDescricao()
            ));
        }
        return listaAplicacoes;
    }

    public RetornoServicoBase deletarAplicacaoPorId(Long id) {
        log.info("[START] - Deletar aplicacao por id");
        var aplicacao = aplicacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial não encontrado."));
        aplicacaoRepository.delete(aplicacao);
        return RetornoServicoBase.positivo("Aplicacao "+ aplicacao.getTipo() + " foi deletada.");
    }

    public Aplicacao buscarAplicacaoPorId(Long aplicacaoId) {
        return aplicacaoRepository.findById(aplicacaoId)
                .orElseThrow(() -> new RuntimeException("Aplicação não encontrada"));
    }

    public List<Aplicacao> buscarTodasAplicacoes() {
        return aplicacaoRepository.findAll();
    }
}
