package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.gerenciador.dto.FormaPagamento;
import com.gerenciador.frota.aplicacao.gerenciador.dto.StatusPagamento;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.NotaFiscalRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.NotaFiscalRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.ParcelaRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.NotaFiscal;
import com.gerenciador.frota.aplicacao.gerenciador.model.Parcela;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class NotaFiscalService {
    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    public NotaFiscal gerarIsercoesDasNotasFiscais(NotaFiscalRequest notaFiscalRequest) {
        log.info("[START] - Gerar um registro de nota fiscal");
        var notaSalva = new NotaFiscal();
        var aVista = FormaPagamento.A_VISTA;
        var aVistaAPrazo = FormaPagamento.A_VISTA_A_PRAZO;
        var parcelado = FormaPagamento.PARCELADO;
        var notaFiscal = notaFiscalRequest.construirNotaFiscal();

        if (notaFiscalRequest.getFormaDePagamento().equals(aVista)){
            log.info("[INFO] - Pagamento avista.");
            notaFiscal.setDescricao(aVista.getDescricao());
            notaFiscal.setStatus(StatusPagamento.PAGO);
            notaSalva = notaFiscalRepository.save(notaFiscal);
            log.info("[END] - Pagamento registrado.");
        } else if (notaFiscalRequest.getFormaDePagamento().equals(aVistaAPrazo)) {
            log.info("[INFO] - Forma de pagamento avista com prazo de pagamento.");
            notaFiscal.setDescricao(aVistaAPrazo.getDescricao());
            notaFiscal.setStatus(StatusPagamento.AVISTA_A_PRAZO);
            notaSalva = notaFiscalRepository.save(notaFiscal);
            List<Parcela> parcelas = this.gerarParcela(notaFiscalRequest, notaSalva);
            notaFiscal.setParcelas(parcelas);
            log.info("[END] - Pagamento registrado.");
        } else if (notaFiscalRequest.getFormaDePagamento().equals(parcelado)) {
            log.info("[INFO] - Forma de pagamento parcelado em "+ notaFiscalRequest.getQuantidadeDeParcelas() + "x.");
            notaFiscal.setDescricao(parcelado.getDescricao());
            notaFiscal.setStatus(StatusPagamento.EM_ABERTO);
            notaSalva = notaFiscalRepository.save(notaFiscal);
            List<Parcela> parcelas = this.gerarParcela(notaFiscalRequest, notaSalva);
            notaFiscal.setParcelas(parcelas);
            log.info("[END] - Pagamento registrado.");
        }

        return notaSalva;
    }

    private List<Parcela> gerarParcela(NotaFiscalRequest notaFiscalRequest, NotaFiscal notaFiscal) {
        if (DataUtils.calcularDiferencaEmDias(notaFiscal.getDataEmissao(), notaFiscalRequest.getDataPagamento()) < 0){
            throw new RuntimeException("Data de vencimento deve ser maior que a data de hoje.");
        }


        if (notaFiscalRequest.getFormaDePagamento().equals(FormaPagamento.A_VISTA_A_PRAZO)){
            notaFiscalRequest.setQuantidadeDeParcelas(1);
        }

        List<Parcela> listasParcelas = new ArrayList<>();
        double valorDaParcela = notaFiscalRequest.getValorTotalDaNota() / notaFiscalRequest.getQuantidadeDeParcelas();
//        double valorDaParcela = Math.round((notaFiscalRequest.getValorTotalDaNota() / notaFiscalRequest.getQuantidadeDeParcelas()) * 100.0) / 100.0;

        String[] dataParcelasPartilhado = notaFiscalRequest.getDataPagamento().split("/");
        String anoDasDatas = dataParcelasPartilhado[2];
        String mesDasDatas = dataParcelasPartilhado[1];
        String diaDasDatas = dataParcelasPartilhado[0];

        for (int i = 1; i <= notaFiscalRequest.getQuantidadeDeParcelas(); i++) {
            Parcela parcela = Parcela.builder()
                    .notaFiscal(notaFiscal)
                    .dataVencimento(diaDasDatas+ "/" + mesDasDatas + "/" + anoDasDatas)
                    .statusPagamento(StatusPagamento.EM_ABERTO)
                    .descricaoParcela(i + "/" + notaFiscalRequest.getQuantidadeDeParcelas())
                    .valorParcela(valorDaParcela)
                    .build();

            if (mesDasDatas.equals("12")){
                    mesDasDatas = "01";
                    long anoAtual = Long.parseLong(anoDasDatas) + 1;
                    anoDasDatas = String.valueOf(anoAtual);
            } else {
                Long proximoMes = Long.parseLong(mesDasDatas) + 1;
                mesDasDatas = String.format("%02d", proximoMes);
            }

            Parcela parcelaSalva = parcelaRepository.save(parcela);
            listasParcelas.add(parcelaSalva);
            log.info("[INFO] - Parcela " + i + " de "+ notaFiscalRequest.getQuantidadeDeParcelas() + " gerada e salva no valor R$" + parcelaSalva.getValorParcela());
        }
        return listasParcelas;
    }
}
