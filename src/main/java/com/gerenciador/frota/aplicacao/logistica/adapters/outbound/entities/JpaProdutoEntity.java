package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities;

import com.gerenciador.frota.aplicacao.logistica.utils.dto.enums.TipoProduto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUTO", schema = "sc_logistica")
public class JpaProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_produto")
    private Long codigoProduto;

    @Column(name = "nome_produto", unique = true)
    private String nomeProduto;

    @Column(name = "descricao_produto")
    private String descricaoProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_produto")
    private TipoProduto tipoProduto;

    @Column(name = "peso_liquido")
    private Double pesoLiquido;

    @Column(name = "peso_bruto")
    private Double pesoBruto;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "valor_liquido")
    private Double valorLiquido;

    @Column(name = "valor_bruto")
    private Double valorBruto;

    @Column(name = "largura")
    private Double largura;

    @Column(name = "altura")
    private Double altura;

    @Column(name = "comprimento")
    private Double comprimento;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal")
    private JpaNotaFiscalLogisticaEntity jpaNotaFiscalLogisticaEntity;
}
