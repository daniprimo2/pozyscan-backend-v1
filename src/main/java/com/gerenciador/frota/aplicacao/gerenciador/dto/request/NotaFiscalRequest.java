package com.gerenciador.frota.aplicacao.gerenciador.dto.request;

import com.gerenciador.frota.aplicacao.Util.Data.DataUtils;
import com.gerenciador.frota.aplicacao.gerenciador.dto.FormaPagamento;
import com.gerenciador.frota.aplicacao.gerenciador.model.NotaFiscal;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotaFiscalRequest {

    private String numeroNotaFiscal;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaDePagamento;
    private Double valorTotalDaNota;
    private String dataPagamento;
    private Integer quantidadeDeParcelas;
    private String comprovanteNF;

    public NotaFiscal construirNotaFiscal() {
        return NotaFiscal.builder()
                .numero(numeroNotaFiscal)
                .dataEmissao(DataUtils.converterLocalDateParaNossoPadraoData(LocalDate.now()))
                .formaPagamento(formaDePagamento)
                .arquivoNf(comprovanteNF)
                .valorTotal(valorTotalDaNota)
                .build();
    }
}
