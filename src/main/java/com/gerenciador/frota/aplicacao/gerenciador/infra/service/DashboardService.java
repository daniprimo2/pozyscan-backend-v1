package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.gerenciador.dto.CategoriasVeiculos;
import com.gerenciador.frota.aplicacao.gerenciador.dto.FormaPagamento;
import com.gerenciador.frota.aplicacao.gerenciador.dto.StatusPagamento;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.AplicacaoResponse;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.DashboardResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.*;
import com.gerenciador.frota.aplicacao.gerenciador.model.NotaFiscal;
import com.gerenciador.frota.aplicacao.gerenciador.model.Parcela;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Service
public class DashboardService {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AplicacaoRepository aplicacaoRepository;


    public DashboardResponse informacoesDashboard(){
        InfoVeiculos infoVeiculos = totalVeiculos();
        return new DashboardResponse(
                arredondar(dispesasTotais(), 2),
                arredondar(dispesasPagas(),2),
                arredondar(dispesasAPagar(), 2),
                infoVeiculos.getTotaisVeiculos(),
                infoVeiculos.getTotalDaFrotaPropria(),
                infoVeiculos.getTotalDosAgregados(),
                aplicacaoRepository.findInfosAplicacoes(),
                veiculoRepository.findInfosVeiculos()
        );
    }

    public Double dispesasTotais() {
        var todasNotasFiscais = notaFiscalRepository.findAll();
        return todasNotasFiscais.stream().mapToDouble(NotaFiscal::getValorTotal).sum();
    }

    private Double dispesasPagas() {
        var dispesasPagas = notaFiscalRepository.findAll();

        double valorNotaFiscalPaga = dispesasPagas.stream().filter(notaFiscal -> {
            return notaFiscal.getFormaPagamento().equals(FormaPagamento.A_VISTA);
        }).mapToDouble(NotaFiscal::getValorTotal).sum();

        var valorParcelaPaga = parcelaRepository.findAll();

        double valorParcelasPagas = valorParcelaPaga.stream().filter(parcela -> {
            return parcela.getStatusPagamento().equals(StatusPagamento.PAGO);
        }).mapToDouble(Parcela::getValorParcela).sum();

        return valorNotaFiscalPaga + valorParcelasPagas;
    }


    private Double dispesasAPagar() {



        var valorParcelaPaga = parcelaRepository.findAll();

        double valorParcelasPagas = valorParcelaPaga.stream().filter(parcela -> {
            return parcela.getStatusPagamento().equals(StatusPagamento.EM_ABERTO) || parcela.getStatusPagamento().equals(StatusPagamento.AVISTA_A_PRAZO);
        }).mapToDouble(Parcela::getValorParcela).sum();

        return  valorParcelasPagas;
    }


    private InfoVeiculos totalVeiculos() {
        int quantidadeTotalVeiculos = veiculoRepository.findAll().size();
        List<CategoriasVeiculos> categoriasVeiculos =  categoriaRepository.findCategoriasEVeiculos();

        int frotaPropria = categoriasVeiculos.stream().filter(categoriasVeiculos1 -> {
            return categoriasVeiculos1.getNomeCategoria().equals("Frota Propria");
        }).mapToInt(CategoriasVeiculos::getTotalVeiculos).sum();

        int frotaAgregada = categoriasVeiculos.stream().filter(categoriasVeiculos1 -> {
            return categoriasVeiculos1.getNomeCategoria().equals("Transportadora Externa");
        }).mapToInt(CategoriasVeiculos::getTotalVeiculos).sum();

        return new InfoVeiculos(quantidadeTotalVeiculos, frotaPropria, frotaAgregada);
    }

    private String  arredondar(Double valor, int casasDecimais) {
        NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatador.setMinimumFractionDigits(casasDecimais);
        formatador.setMaximumFractionDigits(casasDecimais);
        return formatador.format(valor);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    private class InfoVeiculos{
        private Integer totaisVeiculos;
        private Integer totalDaFrotaPropria;
        private Integer totalDosAgregados;
    }
}
