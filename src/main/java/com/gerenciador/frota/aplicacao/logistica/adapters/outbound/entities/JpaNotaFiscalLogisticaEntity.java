package com.gerenciador.frota.aplicacao.logistica.adapters.outbound.entities;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "NotaFiscalLogistica")
@Table(name = "TB_NOTA_FISCAL_logistica", schema = "sc_logistica")
public class JpaNotaFiscalLogisticaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_NF")
    private Long codigoNotaFiscal;

    @Column(name = "numero_nota_fiscal")
    private String numeroNotaFisal;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "data_emissao")
    private String dataEmissao;

    @ManyToOne
    @JoinColumn(name = "COD_REMESSA")
    private JpaRemessaEntity jpaRemessaEntity;

    @ManyToOne
    @JoinColumn(name = "COD_ENDERECO")
    private Endereco endereco;


}
