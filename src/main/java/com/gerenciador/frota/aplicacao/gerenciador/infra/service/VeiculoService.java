package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.Util.Utils.UtilPaginacao;
import com.gerenciador.frota.aplicacao.autenticacao.dto.response.CategoriaResponse;
import com.gerenciador.frota.aplicacao.autenticacao.dto.response.TipoResponse;
import com.gerenciador.frota.aplicacao.autenticacao.dto.response.VeiculoResponse;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FiltroVeiculoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.VeiculoRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.CategoriaRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TipoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.VeiculoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TipoRepository tipoRepository;

    public Veiculo adicionarVeiculo(VeiculoRequest veiculoRequest) {
        return veiculoRepository.save(veiculoRequest.construirVeiculo(veiculoRequest,
                categoriaRepository,
                tipoRepository));
    }


    public PageImpl<?> buscarVeiculoComFiltro(FiltroVeiculoRequest filtroVeiculoRequest,
                                              Pageable pageable) {
        List<Veiculo> veiculo = this.getVeiculos(filtroVeiculoRequest);
        List<VeiculoResponse> listaTratada = veiculo.stream().map(veiculo1 -> {
            var tipo = TipoResponse.builder()
                    .nome(veiculo1.getTipo_id().getNome())
                    .descricao(veiculo1.getTipo_id().getDescricao())
                    .build();
            var categoria = CategoriaResponse.builder()
                    .nome(veiculo1.getCategoria_id().getNome())
                    .descricao(veiculo1.getCategoria_id().getDescricao())
                    .build();
            return VeiculoResponse.builder()
                    .placa(veiculo1.getPlaca())
                    .ano(veiculo1.getAno())
                    .modelo(veiculo1.getModelo())
                    .tipo(tipo)
                    .categoria(categoria)
                    .build();
        }).toList();
        return UtilPaginacao.obterPaginacao(listaTratada, pageable);
    }

    private List<VeiculoResponse> obterPaginacao(List<VeiculoResponse> veiculo, int pageSize, int pageNumber) {
        if (pageNumber == 0)
            pageNumber = 1;

        int startIndex = (pageNumber - 1) * pageSize;
        int emdIndex = Math.min(startIndex + pageSize, veiculo.size());

        if (emdIndex > veiculo.size()) {
            emdIndex = veiculo.size();
        }

        if (startIndex < 0 || startIndex >= veiculo.size() || emdIndex < 0) {
            return new ArrayList<>();
        }


        return veiculo.subList(startIndex, emdIndex);
    }

    private List<Veiculo> getVeiculos(FiltroVeiculoRequest filtroVeiculoRequest) {
        return veiculoRepository.findByPlacaAndModeloAndTipoAndCategoria(
                filtroVeiculoRequest.getPlaca(),
                filtroVeiculoRequest.getModelo(),
                filtroVeiculoRequest.getTipo().isEmpty() ? 0L : Long.parseLong(filtroVeiculoRequest.getTipo()),
                filtroVeiculoRequest.getCategoria().isEmpty() ? 0L : Long.parseLong(filtroVeiculoRequest.getCategoria())
        );
    }

    public RetornoServicoBase deletarVeiculoPorPLaca(String placa) {
        Veiculo veiculo = veiculoRepository.findById(placa)
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));

        veiculoRepository.delete(veiculo);

        return RetornoServicoBase.positivo("Veiculo de placa: " + veiculo.getPlaca() + " deletado com suceso.");

    }

    public Veiculo buscarVeiculoPorPlaca(String placaVeiculo) {
        return veiculoRepository.findByPlaca(placaVeiculo)
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
    }

    public List<Veiculo> buscarTodosVeiculos() {
        return veiculoRepository.findAll();
    }
}
