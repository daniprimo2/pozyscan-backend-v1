package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.gerenciador.frota.aplicacao.gerenciador.dto.StatusPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_parcelas", schema = "sc_gerenciador")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private NotaFiscal notaFiscal;

    private String dataVencimento;

    private Double valorParcela;

    private String descricaoParcela;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

}
