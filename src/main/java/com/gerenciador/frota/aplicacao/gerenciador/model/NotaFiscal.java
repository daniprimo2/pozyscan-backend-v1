package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.gerenciador.frota.aplicacao.gerenciador.dto.FormaPagamento;
import com.gerenciador.frota.aplicacao.gerenciador.dto.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_nota_fiscal", schema = "sc_gerenciador")
public class NotaFiscal {

    @Id
    @Column(name = "numero_nf")
    private String numero;

    @Column(name = "data_emissao", nullable = false)
    private String dataEmissao;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Lob
    @Column(name = "arquivo_nf")
    private String arquivoNf;

    @ManyToOne
    private Fornecedor fornecedor_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_nota_fiscal")
    private StatusPagamento status;

    @Column(name = "descricao_nota_fiscal")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaPagamento formaPagamento;

    @ManyToOne
    private Filial filial;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "notaFiscal")
    private List<Parcela> parcelas;

}
