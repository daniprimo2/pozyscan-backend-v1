package com.gerenciador.frota.aplicacao.gerenciador.model;

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
@Table(name = "tb_nota_fiscal", schema = "sc_gerenciador")
public class NotaFiscal {

    @Id
    @Column(name = "numero_nf")
    private String numero;

    @Column(name = "data_emissao", nullable = false)
    private String dataEmissao;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "arquivo_nf")
    private String arquivoNf;

    @ManyToOne
    private Fornecedor fornecedor_id;

    @Column(name = "status_nota_fiscal")
    private String status;

    @ManyToOne
    private Filial filial;

}
